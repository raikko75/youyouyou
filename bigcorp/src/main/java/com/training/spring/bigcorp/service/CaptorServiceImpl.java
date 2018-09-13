package com.training.spring.bigcorp.service;

import com.training.spring.bigcorp.config.Monitored;
import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Measure;
import com.training.spring.bigcorp.model.PowerSource;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.service.measure.FixedMeasureService;
import com.training.spring.bigcorp.service.measure.MeasureService;
import com.training.spring.bigcorp.service.measure.RealMeasureService;
import com.training.spring.bigcorp.service.measure.SimulatedMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CaptorServiceImpl implements CaptorService {

    private CaptorDao captorDao;
    private MeasureService fixed;
    private MeasureService simulated;
    private MeasureService real;

    public CaptorServiceImpl(CaptorDao captorDao,MeasureService fixedMeasureService, @Qualifier("simulatedMeasureService") MeasureService simulated, @Qualifier("realMeasureService")  MeasureService real) {
        this.fixed = fixedMeasureService;
        this.simulated = simulated;
        this.real = real;
        this.captorDao = captorDao;
    }

    @Monitored
    @Override
    public Set<Captor> findBySite(String siteId) {
        Set<Captor> captors = new HashSet<>();
        if (siteId == null) {
            return captors;
        }
        return captorDao.findBySiteId(siteId).stream().collect(Collectors.toSet());
    }
}

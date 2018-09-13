package com.training.spring.bigcorp.service;

import com.training.spring.bigcorp.config.Monitored;
import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Transactional
@Service
public class SiteServiceImpl implements SiteService {

    private final static Logger logger = LoggerFactory.getLogger(SiteService.class);
    private CaptorService captorService;

    private ResourceLoader resourceLoader;
    public SiteServiceImpl(){


    }


    @Autowired
    public SiteServiceImpl(CaptorService captorService, ResourceLoader ressourceLoader) {
        logger.info("Init SiteServiceImpl :" + this);
        this.resourceLoader = ressourceLoader;
        this.captorService = captorService;
    }

    @Monitored
    @Override
    public Site findById(String siteId) {
        if (siteId == null) {
            return null;
        }
        logger.info("Appel de findById :" + this);
        Site site = new Site("Florange");
        site.setId(siteId);
        site.setCaptors(captorService.findBySite(siteId));
        return site;
    }


}

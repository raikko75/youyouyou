package com.training.spring.bigcorp.service;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.PowerSource;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.service.measure.MeasureService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Set;


@ContextConfiguration(classes = {MeasureServiceConfigurationTest.class})
public class CaptorServiceImplTest {
    @Mock
    private MeasureService fixed;
    @Mock
    private MeasureService simulated;
    @Mock
    private MeasureService real;
    @Mock
    private CaptorDao captorDao;
    @InjectMocks
    private CaptorServiceImpl captorService ;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findBySiteShouldReturnNullWhenIdIsNull() {
        // Initialisation
        String siteId = null;

        // Appel du SUT
        Set<Captor> captors = captorService.findBySite(siteId);

        // Vérification
        Assertions.assertThat(captors).isEmpty();
    }


    @Test
    public void findBySite() {

        Mockito.when(captorDao.findBySiteId(Mockito.eq("1"))).thenReturn(Arrays.asList(new Captor("name", PowerSource.FIXED)));
        Set<Captor> captors = captorService.findBySite("1");
        Assertions.assertThat(captors).hasSize(1).extracting(Captor::getName).containsExactly("name");

    }
}
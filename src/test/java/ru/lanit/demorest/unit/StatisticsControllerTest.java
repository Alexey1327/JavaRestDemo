package ru.lanit.demorest.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.lanit.demorest.config.AppConfig;
import ru.lanit.demorest.intergration.StatController;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
@WebAppConfiguration
public class StatisticsControllerTest {

    @Autowired
    private StatController statController;

    @Test
    public void testStatistics() {
        assertEquals(statController.statisticsAction().getStatusCode(), HttpStatus.OK);
    }
}
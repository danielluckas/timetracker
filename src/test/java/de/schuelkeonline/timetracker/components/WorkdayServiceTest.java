/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components;

import de.schuelkeonline.timetracker.components.beans.Workday;
import java.time.temporal.WeekFields;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author danielschuelke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WorkdayServiceTest.class})

public class WorkdayServiceTest {
    
    public WorkdayServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
     public void shouldResolveWorkdaysForWeekInYear() {
         WorkdayService service = new WorkdayService();
         List<Workday> workdays = service.getWorkdaysForWeekInYear(23,2014);
         assertNotNull(workdays);
         assertEquals(7, workdays.size());
         assertEquals("2014-06-09", workdays.get(0).getDate().toString());
         assertEquals(0, workdays.get(0).getProjectTimes().size());
         assertEquals(0, workdays.get(0).getBreakTimes().size());
         assertEquals("2014-06-10", workdays.get(1).getDate().toString());
         assertEquals("2014-06-11", workdays.get(2).getDate().toString());
         assertEquals("2014-06-12", workdays.get(3).getDate().toString());
         assertEquals("2014-06-13", workdays.get(4).getDate().toString());
         assertEquals("2014-06-14", workdays.get(5).getDate().toString());
         assertEquals("2014-06-15", workdays.get(6).getDate().toString());
     }
     
     
     @Bean
     public WorkdayRepository getTestRepository(){
         WorkdayRepository repo = Mockito.mock(WorkdayRepository.class);
         Mockito.when(repo.findByDate(any())).thenReturn(null);
         return repo;
     }
}

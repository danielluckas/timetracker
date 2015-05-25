/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components;

import de.schuelkeonline.timetracker.components.beans.Workday;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author danielschuelke
 */
@Service
public class WorkdayService {

    @Autowired
    private WorkdayRepository workDayRepository;
    
    public List<Workday> getWorkdaysForWeekInYear(int week, int year) {
        List<Workday> workdayinWeek = new ArrayList<>(7);
        
        LocalDate firstDateOfWeekInYear = findFirstDayOfWeek(week, year);
        
        for(int i = 0; i < 7; i++){
            LocalDate weekday = firstDateOfWeekInYear.plusDays(i);
            Workday workday = null;
            if(workDayRepository != null){
                workday = workDayRepository.findByDate(weekday);
            }
            if(workday== null){
                workday = new Workday();
                workday.setDate(weekday);
            }
            workdayinWeek.add(workday);
        }
            
        
        
        return workdayinWeek;
    }

    private LocalDate findFirstDayOfWeek(int week, int year) {
       LocalDate firstDateOfWeekInYear = null;
       LocalDate startDate = LocalDate.of(year, Month.JANUARY, 1);
       WeekFields weekFields = WeekFields.of(Locale.GERMAN);
       int currentWeekNumber = startDate.get(weekFields.weekOfWeekBasedYear());
       while(currentWeekNumber < week){
           startDate = startDate.plusDays(7);
        currentWeekNumber = startDate.get(weekFields.weekOfWeekBasedYear());
        
       }
       firstDateOfWeekInYear = startDate.with(DayOfWeek.MONDAY);
       return firstDateOfWeekInYear;
    }
}
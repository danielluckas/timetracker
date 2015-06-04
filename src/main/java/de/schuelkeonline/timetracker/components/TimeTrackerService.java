/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components;

import de.schuelkeonline.timetracker.components.beans.BreakTime;
import de.schuelkeonline.timetracker.components.beans.TimeTrackerData;
import de.schuelkeonline.timetracker.components.beans.Workday;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 *
 * @author danielschuelke
 */

@Service
public class TimeTrackerService {
    
    
    private static final WeekFields weekFields = WeekFields.of(Locale.GERMAN);

    @Autowired private WorkdayService workdayService;
    @Autowired private TimeTrackerData data;

    public TimeTrackerService() {
    }
    
    
    public void setNewCurrentDate(LocalDate date){
        updateWeekData(date);
    }
    
    private void updateWeekData(LocalDate currentDate) {
        int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
        int year = currentDate.getYear();
        data.setCurrentDate(currentDate);
        data.setWeekNumber(weekNumber);
        data.setYear(year);
        List<Workday> workdays = workdayService.getWorkdaysForWeekInYear(weekNumber, year);
        data.setWorkdays(workdays);
        data.setSelectedWorkday(workdays.get(0));
    }
    
    @Bean 
    public TimeTrackerData getTimeTrackerData(){
        TimeTrackerData timeTrackerData = new TimeTrackerData();
        List<Workday> workdays = workdayService.getWorkdaysForWeekInYear(timeTrackerData.getWeekNumber(), timeTrackerData.getYear());
        timeTrackerData.setWorkdays(workdays);
        for(Workday day: workdays){
            if(day.getDate().getDayOfMonth() == timeTrackerData.getCurrentDate().getDayOfMonth()){
                timeTrackerData.setSelectedWorkday(day);
            }
        }
        return timeTrackerData;
    }
  

    public void setNextWeek() {
        LocalDate newDate = data.getCurrentDate().plusDays(7);
        updateWeekData(newDate);
    }
    
    public void setPreviousWeek() {
        LocalDate newDate = data.getCurrentDate().minusDays(7);
        updateWeekData(newDate);
    }

    public void selectWorkday(Workday newSelectedWorkday) {
        if(data.getWorkdays().contains(newSelectedWorkday)){
            data.setSelectedWorkday(newSelectedWorkday);
        }
        
    }

    public void saveWorkday(Workday workday) {
        workdayService.saveWorkday(workday);
        updateBalance();
    }

    private void updateBalance() {
        
        float weekBalance = 0.00f;
        weekBalance = data.getWorkdays().stream().map((day) -> day.getDayBalance()).reduce(weekBalance, (accumulator, _item) -> accumulator + _item);
        data.getBalance().setWeekBalance(weekBalance);
        
    }
    
}

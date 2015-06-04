/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.beans;

import de.schuelkeonline.timetracker.db.LocalDatePersistenceConverter;
import de.schuelkeonline.timetracker.db.LocalTimePersistenceConverter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author danielschuelke
 */
@Entity
public class Workday {

    @Id
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate date;
   
    @Convert(converter = LocalTimePersistenceConverter.class)
    private LocalTime beginTime = LocalTime.of(7, 0);
    
    @Convert(converter = LocalTimePersistenceConverter.class)
    private LocalTime endTime = LocalTime.of(16,0);
    
    @OneToMany(mappedBy="workday")
    private List<ProjectTime> projectTimes;
    
    @OneToMany(mappedBy="workday")
    private List<BreakTime> breakTimes;

    public Workday() {
    
        projectTimes = new ArrayList<>();
        breakTimes = new ArrayList<>();
   
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<ProjectTime> getProjectTimes() {
        return projectTimes;
    }

    public void setProjectTimes(List<ProjectTime> projectTimes) {
        this.projectTimes = projectTimes;
    }

    public List<BreakTime> getBreakTimes() {
        return breakTimes;
    }

    public void setBreakTimes(List<BreakTime> breakTimes) {
        this.breakTimes = breakTimes;
    }

    @Override
    public String toString() {
        return date.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public Float getDayBalance() {
        float dayBalance = 0.00f;
        if(beginTime != null && endTime != null){
            int beginTimeSeconds = beginTime.toSecondOfDay();
            int endTimeSeconds = endTime.toSecondOfDay();
            int balanceSeconds = endTimeSeconds - beginTimeSeconds;
            int hours = balanceSeconds / 3600;
            int minutes = (balanceSeconds - (hours * 3600)) / 60;
            float minutesF = 0.00f;
            if(minutes != 0){
                minutesF =  minutes / 60f;
            }
            dayBalance = (float) hours;
            float minutesFloat =  minutesF;
            dayBalance += minutesFloat;
            BigDecimal bd = new BigDecimal(Float.toString(dayBalance));
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            dayBalance = bd.floatValue();
            
        }
        return dayBalance ;
    }
    
}

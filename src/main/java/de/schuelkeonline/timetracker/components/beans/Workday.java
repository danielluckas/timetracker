/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.beans;

import de.schuelkeonline.timetracker.db.LocalDatePersistenceConverter;
import de.schuelkeonline.timetracker.db.LocalTimePersistenceConverter;
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
    private LocalTime beginTime;
    
    @Convert(converter = LocalTimePersistenceConverter.class)
    private LocalTime endTime;
    
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Component;

/**
 *
 * @author danielschuelke
 */
public class TimeTrackerData {

    private static final WeekFields weekFields = WeekFields.of(Locale.GERMAN);
    private boolean editMode;
    private LocalDate currentDate;
    private int weekNumber;
    private int year;
    private Balance balance;
    private List<Workday> workdays;
    private  Workday selectedWorkday;
    public TimeTrackerData() {
        currentDate = LocalDate.now();
        weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
        year = currentDate.getYear();
        balance = new Balance();
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public void setWorkdays(List<Workday> workdays) {
        this.workdays = workdays;
    }

    public List<Workday> getWorkdays() {
        return workdays;
    }

    public Workday getSelectedWorkday() {
       
        
        if(selectedWorkday != null){
            return selectedWorkday;
        }
        
        if(workdays != null && workdays.size()>0){
            return workdays.get(0);
        }
        return null;
    }

    public void setSelectedWorkday(Workday selectedWorkday) {
        this.selectedWorkday = selectedWorkday;
        System.out.println("Selected workday " + selectedWorkday.getDate().format(DateTimeFormatter.ISO_DATE));
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.beans;

import de.schuelkeonline.timetracker.db.LocalDatePersistenceConverter;
import java.time.LocalDate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author danielschuelke
 */
@Entity
public class BreakTime {
    
    @Id
    private long id;
    
    @ManyToOne
    @JoinColumn(name="DATE")
    private Workday workday;
    private Float breakHours;
    private boolean defaultBreak;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Workday getWorkday() {
        return workday;
    }

    public void setWorkday(Workday workday) {
        this.workday = workday;
    }

    public Float getBreakHours() {
        return breakHours;
    }

    public void setBreakHours(Float breakHours) {
        this.breakHours = breakHours;
    }

    public boolean isDefaultBreak() {
        return defaultBreak;
    }

    public void setDefaultBreak(boolean defaultBreak) {
        this.defaultBreak = defaultBreak;
    }

    
    
}

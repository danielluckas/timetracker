/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author danielschuelke
 */
@Entity
public class ProjectTime {
    
    @Id
    private long id;
    
    @ManyToOne
    @JoinColumn(name="DATE")
    private Workday workday;
    @ManyToOne
    @JoinColumn(name="PROJEC_ID")
    private Project project;
    private Float logHours;

    public Workday getWorkday() {
        return workday;
    }

    public void setWorkday(Workday workday) {
        this.workday = workday;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Float getLogHours() {
        return logHours;
    }

    public void setLogHours(Float logHours) {
        this.logHours = logHours;
    }
    
    
    
}

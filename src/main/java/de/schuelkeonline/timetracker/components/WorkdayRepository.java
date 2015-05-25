/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components;

import de.schuelkeonline.timetracker.components.beans.Workday;
import java.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author danielschuelke
 */
public interface WorkdayRepository extends CrudRepository<Workday, LocalDate>{

    public Workday findByDate(LocalDate weekday);
    
}

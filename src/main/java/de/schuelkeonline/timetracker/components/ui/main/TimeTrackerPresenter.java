/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.main;

import de.schuelkeonline.timetracker.components.WorkdayService;
import de.schuelkeonline.timetracker.components.beans.Workday;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author danielschuelke
 */
@Component
public class TimeTrackerPresenter {

    private LocalDate currentDate;
    private int weekNumber;
    private int year;
    private List<Workday> workdays;
    
    @Autowired
    public WorkdayService workdayService;
    
    @FXML
    public Label weekNumberLabel;
    
    @FXML
    public Label yearLabel;
    
    @FXML
    public ListView<Workday> workdayList;
    
    @FXML
    public void initialize() {
        currentDate = LocalDate.now();
        updateView();
    }

    private void updateView() {
        updateTimeRangeView();
        
        List<Workday> workdays = workdayService.getWorkdaysForWeekInYear(weekNumber, year);
        ObservableList<Workday> observableList = FXCollections.observableArrayList(workdays);
        workdayList.setItems(observableList);
    }

    private void updateTimeRangeView() {
        WeekFields weekFields = WeekFields.of(Locale.GERMAN);
        weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
        String currentWeekNumber = String.valueOf(weekNumber);
        year = currentDate.getYear();
        String currentYear = String.valueOf(year);
        weekNumberLabel.setText(currentWeekNumber);
        yearLabel.setText(currentYear);
        System.out.println("Set range to : " + currentWeekNumber + " / " + currentYear);
    }
    
    @FXML
    public void handleNextWeek(){
        currentDate = currentDate.plusDays(7);
        updateView();
        
    }
    
    @FXML
    public void handlePreviousWeek(){
        currentDate = currentDate.minusDays(7);
        updateView();
        
    }

    
    

}

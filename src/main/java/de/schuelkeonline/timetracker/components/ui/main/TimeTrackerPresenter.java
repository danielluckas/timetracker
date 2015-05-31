/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.main;

import de.schuelkeonline.timetracker.components.WorkdayService;
import de.schuelkeonline.timetracker.components.beans.Balance;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
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
    public Label monthBalanceLabel;
    
     @FXML
    public Label weekBalanceLabel;
     
     @FXML
    public Label totalBalanceLabel;
    
    @FXML
    public void initialize() {
        currentDate = LocalDate.now();
        updateView();
    }

    private void updateView() {
        updateTimeRangeView();
        
        updateBalance();
        
        List<Workday> workdays = workdayService.getWorkdaysForWeekInYear(weekNumber, year);
        ObservableList<Workday> observableList = FXCollections.observableArrayList(workdays);
        workdayList.setItems(observableList);
        workdayList.setOrientation(Orientation.HORIZONTAL);
        workdayList.setCellFactory(new Callback<ListView<Workday>, ListCell<Workday>>()
        {
            @Override
            public ListCell<Workday> call(ListView<Workday> listView)
            {
                return new WorkdayViewCell();
            }
        });
        
        workdayList.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                Workday selectedWorkday = workdayList.getSelectionModel().getSelectedItem();
                updateWorkdayDetailView(selectedWorkday);
            }
            
        });
    }

    private void updateTimeRangeView() {
        WeekFields weekFields = WeekFields.of(Locale.GERMAN);
        weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
        String currentWeekNumber = "KW " + String.valueOf(weekNumber);
        year = currentDate.getYear();
        String currentYear = String.valueOf(year);
        weekNumberLabel.setText(currentWeekNumber);
        yearLabel.setText(currentYear);
        System.out.println("Set range to : " + currentWeekNumber + " / " + currentYear);
    }
    
    private void updateWorkdayDetailView(Workday workday){
        if(workday != null && workday.getDate() != null){
            System.out.println("Active workday : " + workday.getDate().toString());
        }
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

    private void updateBalance() {
        Balance balance = new Balance();

        String weekBalance =String.valueOf(balance.getWeekBalance());
        String monthBalance = String.valueOf(balance.getMonthBalance());
        String totalBalance = String.valueOf(balance.getTotalBalance());
        monthBalanceLabel.setText(monthBalance);
        weekBalanceLabel.setText(weekBalance);
        totalBalanceLabel.setText(totalBalance);
    }

    
    

}

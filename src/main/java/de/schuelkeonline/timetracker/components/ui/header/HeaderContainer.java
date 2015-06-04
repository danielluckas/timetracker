/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.header;

import de.schuelkeonline.timetracker.components.TimeTrackerService;
import de.schuelkeonline.timetracker.components.beans.Balance;
import de.schuelkeonline.timetracker.components.beans.TimeTrackerData;
import de.schuelkeonline.timetracker.components.ui.main.TimeTrackerPresenter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author danielschuelke
 */
@Component
public class HeaderContainer {
    
    @FXML
    private AnchorPane anchorPane;
    
    @Autowired
    private TimeTrackerPresenter presenter;
    
    @Autowired
    private TimeTrackerService service;
    
    @Autowired
    private TimeTrackerData data;
    
    
    @FXML private Label weekNumberLabel;
    @FXML private Label yearLabel;
    @FXML private Label monthBalanceLabel;
    @FXML private Label weekBalanceLabel;
    @FXML private Label totalBalanceLabel;
    
    
    public HeaderContainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("headerContainer.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        
    }

    
    
    public void updateView(){
        
        String currentWeekNumber = "KW " + String.valueOf(data.getWeekNumber());
        String currentYear = String.valueOf(data.getYear());
        weekNumberLabel.setText(currentWeekNumber);
        yearLabel.setText(currentYear);
        
        Balance balance = data.getBalance();
        String weekBalance =String.format("%.2f",balance.getWeekBalance());
        String monthBalance = String.format("%.2f",balance.getMonthBalance());
        String totalBalance = String.format("%.2f",balance.getTotalBalance());
        monthBalanceLabel.setText(monthBalance);
        weekBalanceLabel.setText(weekBalance);
        totalBalanceLabel.setText(totalBalance);
        
    }

     @FXML
    public void handleNextWeek(){
        
        service.setNextWeek();
        presenter.updateView();
    }
    
    @FXML
    public void handlePreviousWeek(){
        service.setPreviousWeek();
        presenter.updateView();
    }

    public Node getContainer() {
        return anchorPane;
    }
    
}

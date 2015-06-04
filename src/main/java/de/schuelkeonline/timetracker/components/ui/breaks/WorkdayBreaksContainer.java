/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.breaks;

import de.schuelkeonline.timetracker.components.beans.TimeTrackerData;
import de.schuelkeonline.timetracker.components.beans.Workday;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author danielschuelke
 */
@Component
public class WorkdayBreaksContainer {
    
    @FXML
    private Node anchorPane;

    @Autowired
    private TimeTrackerData data;
    
    private Workday workday;
    
    public WorkdayBreaksContainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workdayBreaksContainer.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
    }
    
    public void updateView(){
        workday = data.getSelectedWorkday();
        if(workday.getBeginTime() == null){
            anchorPane.setVisible(false);
        }else {
            anchorPane.setVisible(true);
        }
    }
    
    public Node getContainer(){
        return anchorPane;
    }
    
}

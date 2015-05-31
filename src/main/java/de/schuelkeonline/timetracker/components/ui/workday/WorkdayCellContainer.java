/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.workday;

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

/**
 *
 * @author danielschuelke
 */
public class WorkdayCellContainer {
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label dayLabel;
    @FXML
    private Label weekdayLabel;
    
    @FXML
    private Label countLabel;
    
    @FXML
    private Label balanceLabel;
    
    private Workday workday;

    public WorkdayCellContainer(Workday workday) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workdayCellContainer.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        this.workday = workday;
        dayLabel.setText(workday.getDate().format(DateTimeFormatter.ofPattern("dd")));
        weekdayLabel.setText(workday.getDate().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.GERMAN));
        countLabel.setText(String.valueOf(workday.getProjectTimes().size()));
        
        balanceLabel.setText(new DecimalFormat("#,##").format(workday.getDayBalance()));
        
    }
    
    public Node getContainer(){
        return anchorPane;
    }
    
}

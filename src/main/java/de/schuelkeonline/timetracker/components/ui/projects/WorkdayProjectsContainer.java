/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.projects;

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
public class WorkdayProjectsContainer {
    
    @FXML
    private Node anchorPane;

    private Workday workday;

    public WorkdayProjectsContainer(Workday workday) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workdayProjectsContainer.fxml"));
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
        
    }
    
    public Node getContainer(){
        return anchorPane;
    }
    
}

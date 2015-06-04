/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.breaks;

import de.schuelkeonline.timetracker.components.beans.BreakTime;
import de.schuelkeonline.timetracker.components.beans.TimeTrackerData;
import de.schuelkeonline.timetracker.components.beans.Workday;
import de.schuelkeonline.timetracker.components.ui.main.TimeTrackerPresenter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author danielschuelke
 */
public class BreakTimeCellContainer {
    
    @FXML
    private AnchorPane anchorPane;
    
    
    @FXML
    private Label breakTimeLabel;
    
    @FXML
    private Label breakTextLabel;
    private final Workday workday;
    
    private final BreakTime breakTime;
    private final TimeTrackerPresenter presenter;

    public BreakTimeCellContainer(TimeTrackerPresenter presenter, Workday workday, BreakTime breakTime) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workdayBreakCellContainer.fxml"));
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
        this.breakTime = breakTime;
                
        breakTimeLabel.setText(String.format("%.2f", breakTime.getBreakHours()));
        breakTextLabel.setText("Automatische Pause");
        this.presenter = presenter;
        
        
    }
    
    @FXML
    public void handleRemove(){
        workday.getBreakTimes().remove(breakTime);
        presenter.updateView();
    }
    
    public Node getContainer(){
        return anchorPane;
    }
    
}

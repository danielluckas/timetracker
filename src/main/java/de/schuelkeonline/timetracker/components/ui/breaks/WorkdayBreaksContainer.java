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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
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
    
    @Autowired
    private TimeTrackerPresenter presenter;
    
    private Workday workday;
    
    @FXML
    private Label fullBreakTime;
    
    
    @FXML
    private ListView<BreakTime> breaktimeListView;
    
    private ObservableList<BreakTime> observableList;
    
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
        observableList = FXCollections.observableArrayList();
        breaktimeListView.setItems(observableList);        
        breaktimeListView.setCellFactory(new Callback<ListView<BreakTime>, ListCell<BreakTime>>()
        {
            
            @Override
            public ListCell<BreakTime> call(ListView<BreakTime> listView)
            {
                return new BreakTimeViewCell(presenter, workday);
            }
        });
    }
    
    @FXML
    public void addNewBreak(){
        BreakTime breakTime = new BreakTime();
        breakTime.setBreakHours(1.0f);
        workday.getBreakTimes().add(breakTime);
        presenter.updateView();
    }
    
    public void updateView(){
        workday = data.getSelectedWorkday();
        if(workday.getBeginTime() == null){
            anchorPane.setVisible(false);
        }else {
            anchorPane.setVisible(true);
        }
        observableList.clear();
        observableList.addAll(workday.getBreakTimes());
        fullBreakTime.setText(String.format("%.2f", workday.getFullBreakHours()));
    }
    
    public Node getContainer(){
        return anchorPane;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.workdaylist;

import de.schuelkeonline.timetracker.components.TimeTrackerService;
import de.schuelkeonline.timetracker.components.beans.Balance;
import de.schuelkeonline.timetracker.components.beans.TimeTrackerData;
import de.schuelkeonline.timetracker.components.beans.Workday;
import de.schuelkeonline.timetracker.components.ui.main.TimeTrackerPresenter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author danielschuelke
 */
@Component
public class WorkdayListContainer {
    
    @FXML
    private AnchorPane anchorPane;
    
    @Autowired
    private TimeTrackerPresenter presenter;
    
    @Autowired
    private TimeTrackerService service;
    
    @Autowired
    private TimeTrackerData data;

    @FXML
    public ListView<Workday> workdayList;
    private Workday selectedWorkday;
    private ObservableList<Workday> observableList;
    public WorkdayListContainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workdayListContainer.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        observableList = FXCollections.observableArrayList();
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
                Workday newSelectedWorkday = workdayList.getSelectionModel().getSelectedItem();
                service.selectWorkday(newSelectedWorkday);
                presenter.updateView();
            }
            
        });
        
    }

    
    
    public void updateView(){
        observableList.clear();
        observableList.addAll(data.getWorkdays());
        workdayList.getSelectionModel().select(data.getSelectedWorkday()); 
    }

    

    public Node getContainer() {
        return anchorPane;
    }
    
}

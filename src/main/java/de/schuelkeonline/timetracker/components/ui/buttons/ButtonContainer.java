/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.buttons;

import de.schuelkeonline.timetracker.components.TimeTrackerService;
import de.schuelkeonline.timetracker.components.beans.TimeTrackerData;
import de.schuelkeonline.timetracker.components.beans.Workday;
import de.schuelkeonline.timetracker.components.ui.main.TimeTrackerPresenter;
import java.io.IOException;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author danielschuelke
 */
@Component
public class ButtonContainer {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    Button startWorkday;
    @FXML
    Button finishWorkday;
    @FXML
    Button editWorkday;
    @FXML
    Button saveWorkday;

    @Autowired private TimeTrackerData data;
    
    @Autowired
    private TimeTrackerPresenter presenter;
    
    @Autowired
    private TimeTrackerService service;
    
    private Workday workday;
    private boolean editMode;
    public ButtonContainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buttonContainer.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    @FXML
    public void handleStartWorkday(){
        workday.setBeginTime(LocalTime.now());
        data.setEditMode(true);
        presenter.updateView();
    }
    
    @FXML
    public void handleEndWorkday(){
        workday.setEndTime(LocalTime.now());
        service.saveWorkday(workday);
        data.setEditMode(false);
        presenter.updateView();
    }
    
    @FXML
    public void handleEditWorkday(){
        data.setEditMode(true);
        presenter.updateView();
    }
    
    @FXML
    public void handleSaveWorkday(){
        
        service.saveWorkday(workday);
        data.setEditMode(false);
        presenter.updateView();
    }

    public void updateView() {
        workday = data.getSelectedWorkday();
        startWorkday.setVisible(false);
        finishWorkday.setVisible(false);
        editWorkday.setVisible(false);
        saveWorkday.setVisible(false);
        if(workday.getBeginTime() == null){
            startWorkday.setVisible(true);
        }else if(workday.getBeginTime() != null && workday.getEndTime() == null){
            finishWorkday.setVisible(true);
        }else if(workday.getBeginTime() != null && workday.getEndTime() != null && !data.isEditMode()){
            editWorkday.setVisible(true);
        }else if(workday.getBeginTime() != null && workday.getEndTime() != null && data.isEditMode()){
            saveWorkday.setVisible(true);
        }
        
        
    }

    public void showStartButton() {
        startWorkday.setVisible(true);

    }

    public void showFinishButton() {
        finishWorkday.setVisible(true);

    }

    public void showEditButton() {
        editWorkday.setVisible(true);

    }

    public void showSaveButton() {
        saveWorkday.setVisible(true);

    }

    public Node getContainer() {
        return anchorPane;
    }

}

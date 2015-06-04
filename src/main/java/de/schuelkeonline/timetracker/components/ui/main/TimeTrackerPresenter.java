/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.main;

import de.schuelkeonline.timetracker.components.ui.breaks.WorkdayBreaksContainer;
import de.schuelkeonline.timetracker.components.ui.buttons.ButtonContainer;
import de.schuelkeonline.timetracker.components.ui.header.HeaderContainer;
import de.schuelkeonline.timetracker.components.ui.projects.WorkdayProjectsContainer;
import de.schuelkeonline.timetracker.components.ui.workday.WorkdayTimeContainer;
import de.schuelkeonline.timetracker.components.ui.workdaylist.WorkdayListContainer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author danielschuelke
 */
@Component
public class TimeTrackerPresenter {

    @Autowired
    private HeaderContainer headerContainer;
    @Autowired
    private WorkdayListContainer listContainer;
    @Autowired
    private WorkdayTimeContainer timeContainer;
    @Autowired
    private WorkdayBreaksContainer breaksContainer;
    @Autowired
    private WorkdayProjectsContainer projectsContainer;
    @Autowired
    private ButtonContainer buttonContainer;

    @FXML
    private VBox mainPane;

    @FXML
    public void initialize() {

//        AnchorPane.setTopAnchor(headerContainer.getContainer(), 0.0);
//        AnchorPane.setTopAnchor(listContainer.getContainer(), 150.0);
//        AnchorPane.setTopAnchor(timeContainer.getContainer(), 280.0);
//        AnchorPane.setTopAnchor(breaksContainer.getContainer(), 500.0);
//        AnchorPane.setTopAnchor(projectsContainer.getContainer(), 700.0);
//        AnchorPane.setBottomAnchor(buttonContainer.getContainer(), 20.0);

        mainPane.getChildren().add(headerContainer.getContainer());
        mainPane.getChildren().add(listContainer.getContainer());
        mainPane.getChildren().add(timeContainer.getContainer());
        mainPane.getChildren().add(breaksContainer.getContainer());
        mainPane.getChildren().add(projectsContainer.getContainer());
        mainPane.getChildren().add(buttonContainer.getContainer());

        updateView();
    }

    public void updateView() {

        headerContainer.updateView();
        listContainer.updateView();
        timeContainer.updateView();
        breaksContainer.updateView();
        projectsContainer.updateView();
        buttonContainer.updateView();

    }

}

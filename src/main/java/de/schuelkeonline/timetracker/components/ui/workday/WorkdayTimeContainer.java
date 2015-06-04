/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.workday;

import de.schuelkeonline.timetracker.components.beans.TimeTrackerData;
import de.schuelkeonline.timetracker.components.beans.Workday;
import de.schuelkeonline.timetracker.components.ui.main.TimeTrackerPresenter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author danielschuelke
 */
@Component
public class WorkdayTimeContainer {

    @Autowired
    private TimeTrackerPresenter presenter;
    
    @FXML
    private Node anchorPane;

    @FXML
    private Label balanceLabel;

    @FXML
    private Button plusHourFromButton;

    @FXML
    private Button plusMinuteFromButton;

    @FXML
    private Button minusHourFromButton;

    @FXML
    private Button minusMinuteFromButton;

    @FXML
    private Button plusHourToButton;

    @FXML
    private Button plusMinuteToButton;

    @FXML
    private Button minusHourToButton;

    @FXML
    private Button minusMinuteToButton;

    @FXML
    private TextField fromHourTextField;

    @FXML
    private TextField fromMinuteTextField;

    @FXML
    private TextField toHourTextField;

    @FXML
    private TextField toMinuteTextField;

    @Autowired
    private TimeTrackerData data;

    private Workday workday;
    
    public WorkdayTimeContainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workdayTimeContainer.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        fromHourTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
//                try {
//                    Integer newHour = Integer.valueOf(newValue);
//                    LocalTime newTime = workday.getBeginTime().withHour(newHour);
//                    workday.setBeginTime(newTime);
//                } catch (Exception e) {
//                    fromHourTextField.setText(oldValue);
//                    System.out.println(e.getMessage());
//                }
//            }
//        });
//
//        toHourTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
//                try {
//                    Integer newHour = Integer.valueOf(newValue);
//                    LocalTime newTime = workday.getEndTime().withHour(newHour);
//                    workday.setEndTime(newTime);
//                } catch (Exception e) {
//                    toHourTextField.setText(oldValue);
//                    System.out.println(e.getMessage());
//                }
//            }
//        });
//
//        fromMinuteTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
//                try {
//                    Integer newMinute = Integer.valueOf(newValue);
//                    LocalTime newTime = workday.getBeginTime().withMinute(newMinute);
//                    workday.setBeginTime(newTime);
//                } catch (Exception e) {
//                    fromMinuteTextField.setText(oldValue);
//                    System.out.println(e.getMessage());
//                }
//            }
//        });
//
//        toMinuteTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
//                try {
//                    Integer newMinute = Integer.valueOf(newValue);
//                    LocalTime newTime = workday.getEndTime().withMinute(newMinute);
//                    workday.setEndTime(newTime);
//                } catch (Exception e) {
//                    toMinuteTextField.setText(oldValue);
//                    System.out.println(e.getMessage());
//                }
//            }
//        });
    }

    public Node getContainer() {
        return anchorPane;
    }

    public void updateView() {
        workday = data.getSelectedWorkday();
        final LocalTime beginTime = workday.getBeginTime();
        final LocalTime endTime = workday.getEndTime();
        String hoursFrom="";
        String minutesFrom = "";
        String hoursTo ="";
        String minutesTo="";
        if(beginTime != null){
             hoursFrom = DateTimeFormatter.ofPattern("HH").format(beginTime);
             minutesFrom = DateTimeFormatter.ofPattern("mm").format(beginTime);
        }else{
            fromHourTextField.setEditable(false);
            fromMinuteTextField.setEditable(false);
        }
        if(endTime != null){
            hoursTo = DateTimeFormatter.ofPattern("HH").format(endTime);
            minutesTo = DateTimeFormatter.ofPattern("mm").format(endTime);
        }else{
            toHourTextField.setEditable(false);
            toMinuteTextField.setEditable(false);
        }
       
        fromHourTextField.setText(hoursFrom);
        fromMinuteTextField.setText(minutesFrom);
        toHourTextField.setText(hoursTo);
        toMinuteTextField.setText(minutesTo);
        
        checkVisibilityHours(beginTime, plusHourFromButton, minusHourFromButton);
        checkVisibilityMinutes(beginTime, plusMinuteFromButton, minusMinuteFromButton);
        checkVisibilityHours(endTime, plusHourToButton, minusHourToButton);
        checkVisibilityMinutes(endTime, plusMinuteToButton, minusMinuteToButton);
        balanceLabel.setText(String.format("%.2f", workday.getDayBalance()));

    }

    @FXML
    public void plusHourFrom() {
        LocalTime localTime = workday.getBeginTime();
        if (localTime != null) {
            localTime = localTime.plusHours(1);
            workday.setBeginTime(localTime);
            updateView();
        }

    }

    @FXML
    public void plusMinuteFrom() {
        LocalTime localTime = workday.getBeginTime();
        if (localTime != null) {
            localTime = localTime.plusMinutes(1);
            workday.setBeginTime(localTime);
            updateView();
        }
    }

    @FXML
    public void minusHourFrom() {
        LocalTime localTime = workday.getBeginTime();
        if (localTime != null) {
            localTime = localTime.minusHours(1);

            workday.setBeginTime(localTime);
            updateView();
        }
    }

    @FXML
    public void minusMinuteFrom() {
        LocalTime localTime = workday.getBeginTime();
        if (localTime != null) {
            localTime = localTime.minusMinutes(1);
            workday.setBeginTime(localTime);
            updateView();
        }
    }

    @FXML
    public void plusHourTo() {
        LocalTime localTime = workday.getEndTime();
        if (localTime != null) {
            localTime = localTime.plusHours(1);
            workday.setEndTime(localTime);
            updateView();
        }
    }

    @FXML
    public void plusMinuteTo() {
        LocalTime localTime = workday.getEndTime();
        if (localTime != null) {
            localTime = localTime.plusMinutes(1);
            workday.setEndTime(localTime);
            updateView();
        }
    }

    @FXML
    public void minusHourTo() {
        LocalTime localTime = workday.getEndTime();
        if (localTime != null) {
            localTime = localTime.minusHours(1);
            workday.setEndTime(localTime);
            updateView();
        }
    }

    @FXML
    public void minusMinuteTo() {
        LocalTime localTime = workday.getEndTime();
        if (localTime != null) {
            localTime = localTime.minusMinutes(1);
            workday.setEndTime(localTime);
            updateView();
        }
    }

    private void checkVisibilityHours(LocalTime time, Button plusButton, Button minusButton) {
        if (time != null && data.isEditMode()) {
            int hour = time.getHour();
            int minute = time.getMinute();
            if (hour == 0) {
                plusButton.setVisible(true);
                minusButton.setVisible(false);
            } else if (hour == 23) {
                plusButton.setVisible(false);
                minusButton.setVisible(true);
            } else {
                plusButton.setVisible(true);
                minusButton.setVisible(true);
            }
        } else {
            plusButton.setVisible(false);
            minusButton.setVisible(false);
        }
    }

    private void checkVisibilityMinutes(LocalTime time, Button plusButton, Button minusButton) {
        if (time != null && data.isEditMode()) {
            int minute = time.getMinute();
            int hour = time.getHour();
            if (hour == 0 && minute == 0) {
                plusButton.setVisible(true);
                minusButton.setVisible(false);
            } else if (hour == 23 && minute == 59) {
                plusButton.setVisible(false);
                minusButton.setVisible(true);
            } else {
                plusButton.setVisible(true);
                minusButton.setVisible(true);
            }
        } else {
            plusButton.setVisible(false);
            minusButton.setVisible(false);
        }
    }
}

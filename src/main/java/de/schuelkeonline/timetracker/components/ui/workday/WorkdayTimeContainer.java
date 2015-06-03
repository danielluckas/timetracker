/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.workday;

import de.schuelkeonline.timetracker.components.beans.Workday;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author danielschuelke
 */
public class WorkdayTimeContainer {
    
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
    
    private Workday workday;

    public WorkdayTimeContainer(Workday workday) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workdayTimeContainer.fxml"));
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
        updateView();
        fromHourTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                try{
                    Integer newHour = Integer.valueOf(newValue);
                    LocalTime newTime = workday.getBeginTime().withHour(newHour);
                    workday.setBeginTime(newTime);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }finally{
                    updateView();
                }
            }
        });
        
        toHourTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                try{
                    Integer newHour = Integer.valueOf(newValue);
                    LocalTime newTime = workday.getEndTime().withHour(newHour);
                    workday.setEndTime(newTime);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }finally{
                    updateView();
                }
            }
        });
        
        fromMinuteTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                try{
                    Integer newMinute = Integer.valueOf(newValue);
                    LocalTime newTime = workday.getBeginTime().withMinute(newMinute);
                    workday.setBeginTime(newTime);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }finally{
                    updateView();
                }
            }
        });
        
        toMinuteTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                try{
                    Integer newMinute = Integer.valueOf(newValue);
                    LocalTime newTime = workday.getEndTime().withMinute(newMinute);
                    workday.setEndTime(newTime);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }finally{
                    updateView();
                }
            }
        });
    }
    
    public Node getContainer(){
        return anchorPane;
    }

    private void updateView() {
        String hoursFrom = DateTimeFormatter.ofPattern("HH").format(workday.getBeginTime());
        fromHourTextField.setText(hoursFrom);
        
        String minutesFrom = DateTimeFormatter.ofPattern("mm").format(workday.getBeginTime());
        fromMinuteTextField.setText(minutesFrom);
        String hoursTo = DateTimeFormatter.ofPattern("HH").format(workday.getEndTime());
        toHourTextField.setText(hoursTo);
        String minutesTo = DateTimeFormatter.ofPattern("mm").format(workday.getEndTime());
        toMinuteTextField.setText(minutesTo);
        checkVisibilityHours(workday.getBeginTime(), plusHourFromButton, minusHourFromButton);
        checkVisibilityMinutes(workday.getBeginTime(), plusMinuteFromButton, minusMinuteFromButton);
        checkVisibilityHours(workday.getEndTime(), plusHourToButton, minusHourToButton);
        checkVisibilityMinutes(workday.getEndTime(), plusMinuteToButton, minusMinuteToButton);
        balanceLabel.setText(String.valueOf(workday.getDayBalance()));
        
    }
    
    @FXML
    public void plusHourFrom(){
        LocalTime localTime = workday.getBeginTime();
        localTime = localTime.plusHours(1);
        workday.setBeginTime(localTime);
        updateView();
        
    }
    
    @FXML
    public void plusMinuteFrom(){
        LocalTime localTime = workday.getBeginTime();
        localTime = localTime.plusMinutes(1);
        workday.setBeginTime(localTime);
        updateView();
        
    }
    
     @FXML
    public void minusHourFrom(){
        LocalTime localTime = workday.getBeginTime();
        localTime = localTime.minusHours(1);
        workday.setBeginTime(localTime);
        updateView();
        
    }
    
    @FXML
    public void minusMinuteFrom(){
        LocalTime localTime = workday.getBeginTime();
        localTime = localTime.minusMinutes(1);
        workday.setBeginTime(localTime);
        updateView();
        
    }
    
        @FXML
    public void plusHourTo(){
        LocalTime localTime = workday.getEndTime();
        localTime = localTime.plusHours(1);
        workday.setEndTime(localTime);
        updateView();
        
    }
    
    @FXML
    public void plusMinuteTo(){
        LocalTime localTime = workday.getEndTime();
        localTime = localTime.plusMinutes(1);
        workday.setEndTime(localTime);
        updateView();
        
    }
    
     @FXML
    public void minusHourTo(){
        LocalTime localTime = workday.getEndTime();
        localTime = localTime.minusHours(1);
        workday.setEndTime(localTime);
        updateView();
        
    }
    
    @FXML
    public void minusMinuteTo(){
        LocalTime localTime = workday.getEndTime();
        localTime = localTime.minusMinutes(1);
        workday.setEndTime(localTime);
        updateView();
        
    }

    private void checkVisibilityHours(LocalTime time, Button plusButton, Button minusButton) {
        int hour = time.getHour();
        if(hour == 0){
            plusButton.setVisible(true);
            minusButton.setVisible(false);
        }else if(hour == 23){
            plusButton.setVisible(false);
            minusButton.setVisible(true);
        }else{
            plusButton.setVisible(true);
            minusButton.setVisible(true);
        }
    }

    private void checkVisibilityMinutes(LocalTime time, Button plusButton, Button minusButton) {
        int minute = time.getMinute();
        if(minute == 0){
            plusButton.setVisible(true);
            minusButton.setVisible(false);
        }else if(minute == 59){
            plusButton.setVisible(false);
            minusButton.setVisible(true);
        }else{
            plusButton.setVisible(true);
            minusButton.setVisible(true);
        }    }
    
}

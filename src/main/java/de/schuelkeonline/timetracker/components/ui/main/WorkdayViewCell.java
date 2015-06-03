/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.main;

import de.schuelkeonline.timetracker.components.beans.Workday;
import de.schuelkeonline.timetracker.components.ui.workday.WorkdayCellContainer;
import de.schuelkeonline.timetracker.components.ui.workday.WorkdayTimeContainer;
import javafx.scene.control.ListCell;

/**
 *
 * @author danielschuelke
 */
class WorkdayViewCell extends ListCell<Workday> {
    
    
    
    public WorkdayViewCell() {
    }

    @Override
    protected void updateItem(Workday item, boolean empty) {
        super.updateItem(item, empty); 
        if(item != null){
            WorkdayCellContainer container = new WorkdayCellContainer(item);
            setGraphic(container.getContainer());
        }
    }
    
    
    
}

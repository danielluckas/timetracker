/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.ui.breaks;

import de.schuelkeonline.timetracker.components.beans.BreakTime;
import de.schuelkeonline.timetracker.components.beans.Workday;
import de.schuelkeonline.timetracker.components.ui.main.TimeTrackerPresenter;
import de.schuelkeonline.timetracker.components.ui.workdaylist.WorkdayCellContainer;
import javafx.scene.control.ListCell;

/**
 *
 * @author danielschuelke
 */
class BreakTimeViewCell extends ListCell<BreakTime> {
    private final TimeTrackerPresenter presenter;
    private final Workday workday;

    public BreakTimeViewCell(TimeTrackerPresenter presenter, Workday workday ) {
        this.presenter = presenter;
        this.workday = workday;
    }

    @Override
    protected void updateItem(BreakTime breakitem, boolean empty) {
        super.updateItem(breakitem, empty);
        if (breakitem != null) {
            BreakTimeCellContainer container = new BreakTimeCellContainer(presenter, workday, breakitem);
            setGraphic(container.getContainer());
        }
    }
}

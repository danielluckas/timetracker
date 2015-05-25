/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author danielschuelke
 */
public class TimeTrackerPreloader extends Preloader{
    
    private Stage stage;

	public void start(Stage stage) throws Exception {

		this.stage = stage;

		Scene scene = new Scene(new ProgressIndicator(-1), 100, 100);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void handleApplicationNotification(PreloaderNotification pn) {

		if (pn instanceof StateChangeNotification) {
			stage.hide();
		}
	}
    
}

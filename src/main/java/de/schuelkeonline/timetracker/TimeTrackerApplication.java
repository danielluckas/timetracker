package de.schuelkeonline.timetracker;

import de.schuelkeonline.timetracker.components.ui.main.TimeTrackerView;
import java.util.logging.Logger;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class TimeTrackerApplication extends JavaFXBootSupport {

    private static final Logger LOG = Logger.getLogger(TimeTrackerApplication.class.getName());
   @Value("${app.ui.title:Example App}")//
    private String windowTitle;

	@Autowired//
	private TimeTrackerView timeTrackerView;

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle(windowTitle);
		stage.setScene(new Scene(timeTrackerView.getView()));
		stage.setResizable(true);
		stage.centerOnScreen();
		stage.show();
	}

	public static void main(String[] args) {
		launchApp(TimeTrackerApplication.class, args);
	}

}

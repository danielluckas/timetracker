/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author danielschuelke
 */
public abstract class JavaFXBootSupport extends Application{
    
    private static String[] savedArgs;

	private ConfigurableApplicationContext applicationContext;

	@Override
	public void init() throws Exception {
		applicationContext = SpringApplication.run(getClass(), savedArgs);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void stop() throws Exception {

		super.stop();
		applicationContext.close();
	}

	protected static void launchApp(Class<? extends JavaFXBootSupport> appClass, String[] args) {

		JavaFXBootSupport.savedArgs = args;
		Application.launch(appClass, args);
	}
    
}

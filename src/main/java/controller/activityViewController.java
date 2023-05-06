package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Activity;
import model.Project;
import model.User;

public class activityViewController implements Initializable {

	@FXML
	private Label activityName;

	@FXML
	private Label estimatedTime;

	@FXML
	private TextField timeRegistered;

	@FXML
	private Label timeUnaccountedFor;

	private static Activity a;

	private Project p;

	public static double remaining = 0;

	private double timeInput;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		p = startpageController.selectedProject;
		a = ProjectViewController.selectedActivity;

		if (remaining == 0) {
			remaining = a.getEstimatedTime();
		}

		// display the specific activity info
		activityName.setText(a.getName());
		estimatedTime.setText("Estimated time: " + a.getEstimatedTime());

		// display the unaccounted time
		timeUnaccountedFor.setText("Time Unaccounted for: " + remaining);

	}

	@FXML
	void back(ActionEvent event) {
		viewSwitcher.switchTo(View.PROJECTVIEW);
	}

	@FXML
	void submit(ActionEvent event) {
		// calculating the remaining time that is unaccounted for
		try {
			timeInput = Double.parseDouble(timeRegistered.getText());
			if (timeInput < 0) {
				return;
			}
		} catch (Exception e) {
			return;
		}
		if (remaining <= 0) {
			return;
		}
		if (timeInput <= a.getEstimatedTime()) {
			remaining -= timeInput;
		}

		// add the registered time to the users memory
		for (User user : a.getListOfDevelopers()) {
			if (user.isLoggedIn()) {
				user.registerTime(timeInput);
			}
		}

		viewSwitcher.switchTo(View.PROJECTVIEW);
	}

}

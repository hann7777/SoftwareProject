//Saeed
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import model.Activity;
import model.Project;
import model.User;

public class createActivityViewController implements Initializable {

	@FXML
	private TextField activityName;

	@FXML
	private Label assignDevelopersLabel;

	@FXML
	private Label createActivityLabel;

	@FXML
	private TextField estimatedTime;

	@FXML
	private ListView<String> listViewOfDeveloperToBeAdded;

	@FXML
	private Button submitButton;

	@FXML
	private Button backButton;

	private boolean isElementsAdded = false;

	private Activity activity;

	private Project p;

	private ArrayList<User> userToBeAdded = new ArrayList<User>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// extracting the selected project from the startpage
		p = startpageController.selectedProject;
		activity = ProjectViewController.selectedActivity;

		// if the activity that is clicked isnt null, we display the info associated
		for (Activity a : p.getListOfActivities()) {
			if (activity != null && activity.equals(a)) {
					activityName.setText(activity.getName());
					estimatedTime.setText("" + activity.getEstimatedTime());
					listViewOfDeveloperToBeAdded.setVisible(false);
					listViewOfDeveloperToBeAdded.setOpacity(0);
					listViewOfDeveloperToBeAdded.setDisable(true);
					assignDevelopersLabel.setVisible(false);
					assignDevelopersLabel.setOpacity(0);
					assignDevelopersLabel.setDisable(true);
					submitButton.setLayoutY(286);
					estimatedTime.setLayoutY(240);
					createActivityLabel.setText("Editing Activity: " + activity.getName());
				
			}
		}
		/*
		 * adding all developer to the listview once the first time the
		 * createActivityView scene is displayed
		 */
		if (!isElementsAdded) {
			if (listViewOfDeveloperToBeAdded.getItems().isEmpty()) {
				for (User user : p.getListOfDevelopers()) {
					if (!(user.isLoggedIn() && user.isProjectLeader())) {
						listViewOfDeveloperToBeAdded.getItems().add(user.getInitials());
					}
				}
			}
			isElementsAdded = true;

		}

		listViewOfDeveloperToBeAdded
				.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(String item) {
						BooleanProperty observable = new SimpleBooleanProperty();
						observable.addListener((obs, wasSelected, isNowSelected) -> {
							if (isNowSelected) {
								// add the user to the project
								for (User user : Main.library.getDevelopers()) {
									if (user.getInitials().equals(item)) {
										userToBeAdded.add(user);
										break;
									}
								}
							} else {
								// remove the User from the project
								for (User user : Main.library.getDevelopers()) {
									if (user.getInitials().equals(item)) {
										userToBeAdded.remove(user);
										break;
									}
								}
							}
						});
						return observable;
					}
				}));
	}

	@FXML
	void onSubmit(ActionEvent event) {

		if (activityName.getText().isEmpty() || estimatedTime.getText() == null) {

			return;
		}

		if (activity != null) {
			double estimatedTimeConverted = Double.parseDouble(estimatedTime.getText());
			activity.setName(activityName.getText());

			// add the new developers to the activity
			for (User user : userToBeAdded) {
				activity.addDeveloper(user);
			}
			if(!(activity.getRemainingTime()==0)) {
				activity.setEstimatedTime(estimatedTimeConverted);
			}else {
				
				activity.updateRemainingTime((estimatedTimeConverted-activity.getEstimatedTime())*-1);
				activity.setEstimatedTime(estimatedTimeConverted);
			}

		} else {

			// convert from a string to a double
			double estimatedTimeConverted = Double.parseDouble(estimatedTime.getText());

			activity = new Activity(activityName.getText(), estimatedTimeConverted);

			// add the developers to the activity
			for (User user : userToBeAdded) {
				activity.addDeveloper(user);
			}

			// add the activity to the project it was created under
			p.addActivity(activity);
			viewSwitcher.switchTo(View.PROJECTVIEW);
		}
	}

	@FXML
	void back(ActionEvent event) {
		viewSwitcher.switchTo(View.PROJECTVIEW);
	}

}
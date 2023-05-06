package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import model.Activity;
import model.Library;
import model.Project;
import model.User;

public class createActivityViewController implements Initializable {

	@FXML
	private TextField activityName;

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
								for (User user : Library.developers) {
									if (user.getInitials().equals(item)) {
										userToBeAdded.add(user);
										break;
									}
								}
							} else {
								// remove the User from the project
								for (User user : Library.developers) {
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

	@FXML
	void back(ActionEvent event) {
		viewSwitcher.switchTo(View.PROJECTVIEW);
	}

}
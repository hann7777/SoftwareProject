//Hassen
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import model.Activity;
import model.Project;
import model.User;

public class projectleaderActivityViewController implements Initializable {

	@FXML
	private Label activityName;

	@FXML
	private Label estimatedTime;

	@FXML
	private ListView<String> listOfDevelopers;

	@FXML
	private Label timeUnaccountedFor;

	@FXML
	private Button edit;

	@FXML
	private TextFlow hoursWorked;

	private Activity a;

	private Project p;

	private ArrayList<User> userToBeAdded = new ArrayList<User>();

	private Map<String, BooleanProperty> checkboxState = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		p = startpageController.selectedProject;
		a = ProjectViewController.selectedActivity;

		// add the users to the listview
		for (User user : p.getListOfDevelopers()) { 
			listOfDevelopers.getItems().add(user.getInitials());

		}
		// display the hours each person has worked
		for (Map.Entry<String, Double> entry : a.getRegisteredHours().entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();
		    Text bulletPoint = new Text("\u2022 "); 
		    bulletPoint.setFill(Color.BLACK);
		    Text item = new Text(key + " Has Worked: " + value + " hours \n");
		    item.setFill(Color.BLACK);
		    hoursWorked.getChildren().addAll(bulletPoint, item);
		    if(a.getRemainingTime()==0) {
		    	activityName.setText(a.getName() + " Task is Completed");
		    }
		}
		

		// display the specific activity info
		activityName.setText(a.getName());
		estimatedTime.setText("Estimated time: " + a.getEstimatedTime());
		// display the unaccounted time
		timeUnaccountedFor.setText("Time Unaccounted for: " + a.getRemainingTime());

		listOfDevelopers.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(String item) {
				BooleanProperty observable = checkboxState.get(item);
				if (observable == null) {
					observable = new SimpleBooleanProperty();
					checkboxState.put(item, observable);
				}
				observable.addListener((obs, wasSelected, isNowSelected) -> {
					if (isNowSelected) {
						// add the users to the activity listview and the activities list of developers
						for (User user : p.getListOfDevelopers()) {
							if (user.getInitials().equals(item)) {
								userToBeAdded.add(user);
								continue;
							}
						}
					} else {
						// remove the users from the activity listview and the activities list of
						// developers
						for (User user : p.getListOfDevelopers()) {
							if (user.getInitials().equals(item)) {
								userToBeAdded.remove(user);
								continue;
							}
						}
					}
				});

				// Set initial checkbox state to true, if the user is already on the activity
				boolean isChecked = false;
				for (User user : a.getListOfDevelopers()) {
					if (user.getInitials().equals(item)) {
						isChecked = true;
						break;
					}
				}
				observable.set(isChecked);

				return observable;
			}
		}));
	}

	@FXML
	void back(ActionEvent event) {
		viewSwitcher.switchTo(View.PROJECTVIEW);
	}

	@FXML
	void edit(ActionEvent event) {
		viewSwitcher.switchTo(View.CREATEACTIVITYVIEW);
	}

	@FXML
	void submit(ActionEvent event) {

		a.setListOfDevelopers(userToBeAdded);

		viewSwitcher.switchTo(View.PROJECTVIEW);
	}

}
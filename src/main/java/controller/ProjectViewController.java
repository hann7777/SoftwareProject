package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Activity;
import model.Library;
import model.Project;
import model.User;

public class ProjectViewController implements Initializable {

	@FXML
	private Button AddActivityButton;

	@FXML
	private Button apply;

	@FXML
	private TextFlow description;

	@FXML
	private Label endDate;

	@FXML
	private ListView<String> listOfActivitiesOnProject;

	@FXML
	private ListView<String> listOfDevelopersOnProject;

	@FXML
	private Label projectLeaderLabel;

	@FXML
	private Label projectName;

	@FXML
	private Label startDate;

	@FXML
	private Button backButton;

	private String sDateFormatted;

	private String eDateFormatted;

	private Text text;

	public static Activity selectedActivity;

	private Project p;

	private boolean elementIsAdded = false;

	private static int counter = 0;

	private ArrayList<User> userToBeAdded = new ArrayList<User>();

	private Map<String, BooleanProperty> checkboxState = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		p = startpageController.selectedProject;

		// disable the apply button for non project leader
		for (User user : Library.developers) {
			if ((user.isLoggedIn() && !p.getProjectLeader().equals(user.getName()))) {
				apply.setDisable(true);
				apply.setOpacity(0);
			}
		}

		// initialize the info of the specific project
		LocalDate sDate = p.getStartDate();
		LocalDate eDate = p.getEndDate();
		sDateFormatted = sDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		eDateFormatted = eDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		text = new Text(p.getDescription());

		// display the specific projects info
		projectName.setText(p.getName());
		startDate.setText("Project started: " + sDateFormatted);
		endDate.setText("Project ends: " + eDateFormatted);
		description.getChildren().add(text);
		projectLeaderLabel.setText("Project leader: " + p.getProjectLeader());

		// add all developers to the listview of developers
		for (User user : Library.developers) {
			if (!(p.getProjectLeader().equals(user.getName()))) {
				listOfDevelopersOnProject.getItems().add(user.getInitials());
			}

		}
	for(User u : Library.developers) {
	  if (u.isLoggedIn() && p.getProjectLeader().equals(u.getName())) {
		listOfDevelopersOnProject.setCellFactory(CheckBoxListCell.forListView(item -> {
		    BooleanProperty observable = null;
		   
		            observable = new SimpleBooleanProperty();
		            observable.addListener((obs, wasSelected, isNowSelected) -> {
		                if (isNowSelected) {
		                    // add the developers that have been checked to the project
		                    for (User user : p.getListOfDevelopers()) {
		                        if (user.getInitials().equals(item)) {
		                            userToBeAdded.add(user);
		                            break;
		                        }
		                    }
		                } else {
		                    // remove the developers that have been unchecked
		                    for (User user : p.getListOfDevelopers()) {
		                        if (user.getInitials().equals(item)) {
		                            userToBeAdded.remove(user);
		                            break;
		                        }
		                    }
		                }
		            });
		            // Set initial checkbox state to true, if the user is already on the project
		            boolean isChecked = false;
		            for (User user : p.getListOfDevelopers()) {
		                if (user.getInitials().equals(item)) {
		                    isChecked = true;
		                    break;
		                }
		            }
		            observable.set(isChecked);
		            break;
		        
		    
		    return observable;
	  
		}));

	  }else{
		listOfDevelopersOnProject.setCellFactory(TextFieldListCell.forListView());
		}
	}
	
	

	/*
	 * Code regarding the acitivity listview and buttons
	 */

	// add the activities that the specific user is on to the listview
	listOfActivitiesOnProject.getItems().clear();

	for(

	Activity activity:p.getListOfActivities())
	{
		for (User user : activity.getListOfDevelopers()) {
			if (user.isLoggedIn()) {
				if (listOfActivitiesOnProject.getItems().isEmpty()) {
					listOfActivitiesOnProject.getItems().add(activity.getName());

				}
			}

		}
	}

	/*
	 * if the user that is logged in is the projectleader of the project that is
	 * clicked on, then that user can see all activitties associated with the
	 * project
	 */
	for(
	User user:Library.developers)
	{
		if (user.isLoggedIn() && (p.getProjectLeader().equals(user.getName()))) {
			for (Activity activity : p.getListOfActivities()) {
				listOfActivitiesOnProject.getItems().add(activity.getName());
			}

		}

	}

	// remove the addActivity button for developers that isnt a project leader on
	// the specific project
	for(
	User user:Library.developers)
	{
		if (user.isLoggedIn() && !(p.getProjectLeader().equals(user.getName()))) {
			AddActivityButton.setDisable(true);
			AddActivityButton.setOpacity(0);

		}
	}

	// Open the acitivty that has been clicked
	listOfActivitiesOnProject.setOnMouseClicked(e->
	{
		for (Activity activity : p.getListOfActivities()) {
			String selectedItem = listOfActivitiesOnProject.getSelectionModel().getSelectedItem();
			if (activity.getName().equals(selectedItem)) {
				selectedActivity = activity;
				break;

			}
		}
	});
	

	}
 


	@FXML
	void onApply(ActionEvent event) {
		p.setListOfDevelopers(userToBeAdded);

	}

	@FXML
	void openActivity(ActionEvent event) {

		if (selectedActivity != null) {
			// if the user is a projectleader on a project he cant register hours, he can
			// moderate the work
			for (User user : Library.developers) {
				if (user.isLoggedIn() && p.getProjectLeader().equals(user.getName())) {
					viewSwitcher.switchTo(View.PROJECTLEADERACTIVITYVIEW);
					return;
				} else {
					viewSwitcher.switchTo(View.ACTIVITYVIEW);
					return;
				}
			}

		}
	}

	@FXML
	void addActivity(ActionEvent event) {
		viewSwitcher.switchTo(View.CREATEACTIVITYVIEW);
	}

	@FXML
	void back(ActionEvent event) {
		viewSwitcher.switchTo(View.STARTPAGE);

	}

}
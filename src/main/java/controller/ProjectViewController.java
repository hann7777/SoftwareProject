package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	private List<User> userToBeAdded = new ArrayList<>();

	private Map<String, BooleanProperty> checkboxState;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		checkboxState = new HashMap<>();
		p = startpageController.selectedProject;

		// Disable the apply button for non-project leader
		for (User user : Library.developers) {
			if (user.isLoggedIn() && !p.getProjectLeader().equals(user.getName())) {
				apply.setDisable(true);
				apply.setOpacity(0);
			}
		}

		// Initialize the info of the specific project
		LocalDate sDate = p.getStartDate();
		LocalDate eDate = p.getEndDate();
		sDateFormatted = sDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		eDateFormatted = eDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		text = new Text(p.getDescription());

		// Display the specific project's info
		projectName.setText(p.getName());
		startDate.setText("Project started: " + sDateFormatted);
		endDate.setText("Project ends: " + eDateFormatted);
		description.getChildren().add(text);
		projectLeaderLabel.setText("Project leader: " + p.getProjectLeader());

		// Add all developers to the ListView of developers
		for (User user : Library.developers) {
				if(user.isLoggedIn() && p.getProjectLeader().equals(user.getName())) {
					for (User u : Library.developers) {
						listOfDevelopersOnProject.getItems().add(u.getInitials());
						listOfDevelopersOnProject.getItems().remove(user.getInitials());
				}
				
		}
		}
		// Add all developers to the ListView of developers
		for (User user : Library.developers) {
				if(user.isLoggedIn() && !(p.getProjectLeader().equals(user.getName()))) {
					for (User u : p.getListOfDevelopers()) {
						listOfDevelopersOnProject.getItems().add(u.getInitials());
				}
				
		}
		}
		
		

		for (User u : Library.developers) {
			if (u.isLoggedIn() && p.getProjectLeader().equals(u.getName())) {
				listOfDevelopersOnProject.setCellFactory(CheckBoxListCell.forListView(item -> {
					BooleanProperty observable = new SimpleBooleanProperty();
					observable.addListener((obs, wasSelected, isNowSelected) -> {
						checkboxState.put(item, new SimpleBooleanProperty(isNowSelected));
						if (isNowSelected) {
							// Add the developers that have been checked to the project
							for (User user : p.getListOfDevelopers()) {
								if (user.getInitials().equals(item)) {
									userToBeAdded.add(user);
									break;
								}
							}
						} else {
							// Remove the developers that have been unchecked
							for (User user : p.getListOfDevelopers()) {
								if (user.getInitials().equals(item)) {
									userToBeAdded.remove(user);
									break;
								}
							}
						}
					});

					// Set initial checkbox state to true if the user is already on the project
					boolean isChecked = false;
					for (User user : p.getListOfDevelopers()) {
						if (user.getInitials().equals(item)) {
							isChecked = true;
							break;
						}
					}
					observable.set(isChecked);

					// Disable the checkbox if the user has been removed from the project
					if (!isChecked && userToBeAdded.contains(u)) {
						observable.set(false);
						observable.unbind();
					}

					return observable;
				}));
			}
		}

		// Set initial checkbox state based on the project's developers
		for (String initials : listOfDevelopersOnProject.getItems()) {
			User user = getUserByInitials(initials);
			boolean isSelected = p.getListOfDevelopers().contains(user);
			checkboxState.put(initials, new SimpleBooleanProperty(isSelected));
		}

		/*
		 * Code regarding the activity ListView and buttons
		 */

		// Add the activities that the specific user is assigned to the ListView
		listOfActivitiesOnProject.getItems().clear();
		for (Activity activity : p.getListOfActivities()) {
			for (User user : activity.getListOfDevelopers()) {
				if (user.isLoggedIn() && !listOfActivitiesOnProject.getItems().contains(activity.getName())) {
					listOfActivitiesOnProject.getItems().add(activity.getName());
				}
			}
		}

		/*
		 * If the user that is logged in is the project leader of the project that is
		 * clicked on, then that user can see all activities associated with the
		 * project.
		 */
		for (User user : Library.developers) {
			if (user.isLoggedIn() && p.getProjectLeader().equals(user.getName())) {
				for (Activity activity : p.getListOfActivities()) {
					if (!listOfActivitiesOnProject.getItems().contains(activity.getName())) {
						listOfActivitiesOnProject.getItems().add(activity.getName());
					}
				}
			}
		}

		// Remove the AddActivity button for developers who aren't a project leader on
		// the specific project
		for (User user : Library.developers) {
			if (user.isLoggedIn() && !p.getProjectLeader().equals(user.getName())) {
				AddActivityButton.setDisable(true);
				AddActivityButton.setOpacity(0);
			}
		}

		// Open the activity that has been clicked
		listOfActivitiesOnProject.setOnMouseClicked(e -> {
		    for (Activity activity : p.getListOfActivities()) {
		        String selectedItem = listOfActivitiesOnProject.getSelectionModel().getSelectedItem();
		        if (selectedItem != null && activity.getName().equals(selectedItem)) {
		            selectedActivity = activity;
		        	break;
		        }
		    }
		});
		
	}

	@FXML
	void onApply(ActionEvent event) {
		// Clear the list of developers in the project
		p.getListOfDevelopers().clear();

		// Add the selected developers to the project's list of developers
		for (String item : listOfDevelopersOnProject.getItems()) {
			BooleanProperty state = checkboxState.get(item);
			if (state != null && state.get()) {
				for (User user : Library.developers) {
					if (user.getInitials().equals(item)) {
						p.getListOfDevelopers().add(user);
						break;
					}
				}
			}
		}
	}

	@FXML
	void openActivity(ActionEvent event) {
		if (selectedActivity != null) {
			// If the user is a project leader on a project, they can't register hours. They
			// can moderate the work.
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

	// Helper method to get User object by initials
	private User getUserByInitials(String initials) {
		for (User user : Library.developers) {
			if (user.getInitials().equals(initials)) {
				return user;
			}
		}
		return null;
	}

	// Helper method to get Activity object by name
	private Activity getActivityByName(String name) {
		for (Activity activity : p.getListOfActivities()) {
			if (activity.getName().equals(name)) {
				return activity;
			}
		}
		return null;
	}

}

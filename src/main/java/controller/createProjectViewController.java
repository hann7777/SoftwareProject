//Hassen
package controller;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import model.Project;
import model.User;

public class createProjectViewController implements Initializable {

	@FXML
	private Label developerLabel;

	@FXML
	private Label projectInfoLabel;

	@FXML
	private DatePicker endDate;

	@FXML
	private Button submitButton;

	@FXML
	private ListView<String> listviewOfDevelopers;

	@FXML
	private TextField projectDescription;

	@FXML
	private TextField projectName;

	@FXML
	private DatePicker startDate;

	private boolean isElementsAdded = false;

	private Project project;

	private ArrayList<User> userToBeAdded = new ArrayList<User>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		project = startpageController.selectedProject;
		// if the project that is clicked isnt null, we display the info associated
		for (Project p : Main.library.getProjects()) {
			if (project != null && project.equals(p)) {
				projectName.setText(project.getName());
				projectDescription.setText(project.getDescription());
				startDate.setValue(project.getStartDate());
				endDate.setValue(project.getEndDate());
				listviewOfDevelopers.setVisible(false);
				listviewOfDevelopers.setOpacity(0);
				listviewOfDevelopers.setDisable(true);
				developerLabel.setVisible(false);
				submitButton.setLayoutY(375);
				projectInfoLabel.setText("Editing Project: " + project.getName());
			}
		}
		// adding all developer to the listview once the first time the
		// createProjectView is displayed
		if (!isElementsAdded) {
			if (listviewOfDevelopers.getItems().isEmpty()) {
				for (User user : Main.library.getDevelopers()) {
					if (!(user.isLoggedIn() && user.isProjectLeader())) {
						listviewOfDevelopers.getItems().add(user.getInitials());
					}
				}
			}
			isElementsAdded = true;

		}

		listviewOfDevelopers
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
	void submit(ActionEvent event) {
		LocalDate sDate = startDate.getValue();
		LocalDate eDate = endDate.getValue();
		if (projectName.getText().isEmpty() || startDate.getValue() == null || endDate.getValue() == null) {
			return;
		}
		// checking that the start and end date is valid
		if (eDate.compareTo(sDate) < 0 || eDate.compareTo(sDate) == 0 || sDate.isBefore(LocalDate.now())) {
			return;
		}
		// edit the existing project
		if (project != null) {

			project.setName(projectName.getText());
			project.setDescription(projectName.getText());
			project.setStartDate(sDate);
			project.setEndDate(eDate);
			for (User user : userToBeAdded) {
				project.addDeveloper(user);
			}
			// create a completely new project
		} else {

			project = new Project(projectName.getText(), sDate, eDate, projectDescription.getText());

			for (User user : userToBeAdded) {
				project.addDeveloper(user);
			}

			viewSwitcher.switchTo(View.STARTPAGE);

		}
	}

	@FXML
	void back(ActionEvent event) {
		viewSwitcher.switchTo(View.STARTPAGE);
	}

}

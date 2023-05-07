package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import model.Library;
import model.Project;
import model.User;

public class startpageController implements Initializable {

	@FXML
	private Button createProjectButton;

	@FXML
	private Button deleteProjectButton;

	@FXML
	private ListView<String> listviewOfProjects;

	@FXML
	private Button openProjectButton;

	private ProjectViewController pvc;

	public static Project selectedProject;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// add proejct to the listview
		for (User user : Main.library.getDevelopers()) {
			if (user.isLoggedIn()) {
				for (Project project : Main.library.getProjects()) {
					if (project.getProjectLeader().equals(user.getName())) {
						listviewOfProjects.getItems().add(project.getName());
					}
					for (User u : project.getListOfDevelopers()) {
						if (u.equals(user)) {

							listviewOfProjects.getItems().add(project.getName());
						}
					}
				}
			}
		}

		// Open the project that has been clicked
		listviewOfProjects.setOnMouseClicked(e -> {
			for (Project project : Main.library.getProjects()) {
				String selectedItem = listviewOfProjects.getSelectionModel().getSelectedItem();
				if (project.getName().equals(selectedItem)) {
					selectedProject = project;
					break;

				}
			}
		});

		// remove the create project and delete project button for developers that arent
		// hired as project
		// leaders
		for (User user : Main.library.getDevelopers()) {
			if (user.isLoggedIn()) {
				if (!(user.isProjectLeader())) {
					createProjectButton.setDisable(true);
					createProjectButton.setOpacity(0);
					deleteProjectButton.setDisable(true);
					deleteProjectButton.setOpacity(0);
				}
			}
		}
	}

	@FXML
	void onCreateProject(ActionEvent event) {
		listviewOfProjects.getSelectionModel().clearSelection();
		if (listviewOfProjects.getSelectionModel().getSelectedItem() == null) {
			selectedProject = null;
			viewSwitcher.switchTo(View.CREATEPROJECTVIEW);
		}
	}

	@FXML
	void onOpenProject(ActionEvent event) {
		if (selectedProject != null) {
			viewSwitcher.switchTo(View.PROJECTVIEW);
		}

	}

	@FXML
	void onDeleteProject(ActionEvent event) {
		try {
			if (selectedProject != null) {
				for (Project project : Main.library.getProjects()) {
					if (selectedProject.equals(project)) {
						for (User user : Main.library.getDevelopers()) {
							if (user.isLoggedIn()) {
								if (project.getProjectLeader().equals(user.getName())) {
									Main.library.getProjects().remove(selectedProject);
									listviewOfProjects.getItems().remove(project.getName());
								} else {
									return;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}

	}

	@FXML
	void logout(ActionEvent event) {
		for (User user : Main.library.getDevelopers()) {
			user.setLoggedIn(false);
			viewSwitcher.switchTo(View.LOGIN);

		}
	}

}
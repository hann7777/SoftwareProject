package controller;

import java.net.URL;
import java.util.ResourceBundle;

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
	private ListView<String> listviewOfProjects;

	@FXML
	private Button openProjectButton;

	private ProjectViewController pvc;

	public static Project selectedProject;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// add proejct to the listview
        for (User user : Library.developers) {
            if (user.isLoggedIn()) {
                for (Project project : Library.projects) {
                	if(project.getProjectLeader().equals(user.getName())) {
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

		//Open the project that has been clicked
		listviewOfProjects.setOnMouseClicked(e -> {
			for (Project project : Library.projects) {
				String selectedItem = listviewOfProjects.getSelectionModel().getSelectedItem();
				if (project.getName().equals(selectedItem)) {
					selectedProject = project;
					break;

				}
			}
		});
		
		//remove the create project button for developers that arent hired as project leaders
		for (User user : Library.developers) {
				if(user.isLoggedIn()) {
						if(!(user.isProjectLeader())) {
							createProjectButton.setDisable(true);
							createProjectButton.setOpacity(0);
						}
				}
		}
				

	}

	@FXML
	void onCreateProject(ActionEvent event) {
		viewSwitcher.switchTo(View.CREATEPROJECTVIEW);
	}

	@FXML
	void onOpenProject(ActionEvent event) {
		if(selectedProject != null) {
			viewSwitcher.switchTo(View.PROJECTVIEW);

		}

	}
	


	@FXML
	void logout(ActionEvent event) {
		for(User user : Library.developers) {
				user.setLoggedIn(false);
				viewSwitcher.switchTo(View.LOGIN);

	}
	}

	

}
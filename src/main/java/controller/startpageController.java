package controller;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class startpageController {

    @FXML
    private Button createProjectButton;

    @FXML
    private ListView<?> listviewOfProjects;

    @FXML
    private Button openProjectButton;
    
    @FXML
    void onCreateProject(ActionEvent event) {
    	viewSwitcher.switchTo(View.CREATEPROJECTVIEW);
    }
    
    @FXML
    void onOpenProject(ActionEvent event) {
    	viewSwitcher.switchTo(View.PROJECTVIEW);
    }

}
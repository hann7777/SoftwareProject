package controller;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.TextFlow;

public class ProjectViewController {

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
    
    @FXML
    void onApply(ActionEvent event) {
    	
    		
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
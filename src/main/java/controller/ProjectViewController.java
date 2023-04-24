package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.TextFlow;

public class ProjectViewController {

    @FXML
    private Button AddActivityButton;

    @FXML
    private Button addDeveloperButton;

    @FXML
    private TextFlow description;

    @FXML
    private Label endDate;

    @FXML
    private ListView<?> listOfActivitiesOnProject;

    @FXML
    private ListView<?> listOfDevelopersOnProject;

    @FXML
    private Label projectLeaderLabel;

    @FXML
    private Label projectName;

    @FXML
    private Label startDate;
    
    

}
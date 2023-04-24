package controller;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class createActivityViewController {

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
    


    @FXML
    void onSubmit(ActionEvent event) {

    }
    
    @FXML
    void back(ActionEvent event) {
    	viewSwitcher.switchTo(View.PROJECTVIEW);

    }

}
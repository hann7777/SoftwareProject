package controller;

import java.awt.Button;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class createProjectViewController {

    @FXML
    private DatePicker endDate;

    @FXML
    private ListView<String> listviewOfDevelopers;

    @FXML
    private TextField projectDescription;

    @FXML
    private TextField projectName;

    @FXML
    private DatePicker startDate;
    
    
    
    
    @FXML
    void back(ActionEvent event) {
    	viewSwitcher.switchTo(View.STARTPAGE);

    }

}
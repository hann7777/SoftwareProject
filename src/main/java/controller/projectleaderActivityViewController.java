package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import model.Activity;
import model.Library;
import model.Project;
import model.User;

public class projectleaderActivityViewController implements Initializable{

    @FXML
    private Label activityName;

    @FXML
    private Label estimatedTime;

    @FXML
    private ListView<String> listOfDevelopers;

    @FXML
    private Label timeUnaccountedFor;

    
    private Activity a;
    
    private Project p;
    
    private ArrayList<User> userToBeAdded = new  ArrayList<User>();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        p = startpageController.selectedProject;
        a = ProjectViewController.selectedActivity;
        
        // add the users to the listview
        for(User user :  p.getListOfDevelopers()) {
        	listOfDevelopers.getItems().add(user.getInitials());
        }
        

        // display the specific activity info
        activityName.setText(a.getName());
        estimatedTime.setText("Estimated time: " + a.getEstimatedTime());
        // display the unaccounted time
        timeUnaccountedFor.setText("Time Unaccounted for: " + activityViewController.remaining);
        
        
        listOfDevelopers
		.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(String item) {
				BooleanProperty observable = new SimpleBooleanProperty();
				observable.addListener((obs, wasSelected, isNowSelected) -> {
					if (isNowSelected) {
						// add the user to the project
						for (User user : Library.developers) {
							if (user.getInitials().equals(item)) {
								userToBeAdded.add(user);
								break;
							}
						}
					} else {
						// remove the User from the project
						for (User user : Library.developers) {
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
    void back(ActionEvent event) {
    	viewSwitcher.switchTo(View.PROJECTVIEW);

    }
    
    @FXML
    void submit(ActionEvent event) {
    	
    	for(User user : userToBeAdded) {
    		a.addDeveloper(user);
    	}
    	
    	
    	viewSwitcher.switchTo(View.PROJECTVIEW);
    }
	

}
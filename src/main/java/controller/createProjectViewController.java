package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import model.Library;
import model.Project;
import model.User;

public class createProjectViewController implements Initializable {

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

	private boolean isElementsAdded = false;
	
	private Project project;

	@FXML
	void back(ActionEvent event) {
		viewSwitcher.switchTo(View.STARTPAGE);
	}

	public void addDeveloperToListview() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LocalDate date = LocalDate.of(2022, 1, 1);

		new Project("fsdf",date, date, "nofnsonfosnf" );
		new Project("fssfsfsfsdsdf",date, date, "nofadadnf" );
		
		if (!isElementsAdded) {
			// adding all developer to the listview once
			for (User user : Library.developers) {
				listviewOfDevelopers.getItems().add(user.getInitials());
			}
			isElementsAdded = true;

		}
		LocalDate sDate = startDate.getValue();
		LocalDate eDate = endDate.getValue();
		project = new Project(projectName.getText(), sDate, eDate, projectDescription.getText());
	
		listviewOfDevelopers.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(String item) {
						BooleanProperty observable = new SimpleBooleanProperty();
						observable.addListener((obs, wasSelected, isNowSelected) -> {
							if (isNowSelected) {
								// add the user to the project
								for (User user : Library.developers) {
									if (user.getInitials().equals(item)) {
										project.addDeveloper(user);
										break;
									}
								}
							} else {
								// remove the User from the project
								for (User user : Library.developers) {
									if (user.getInitials().equals(item)) {
										project.removeDeveloper(user);
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
		 if (projectName.getText().isEmpty() || startDate.getValue() == null || endDate.getValue() == null) {
		        return;
		    }
		LocalDate sDate = startDate.getValue();
		LocalDate eDate = endDate.getValue();
		project.setName(projectName.getText());
		project.setDescription(projectDescription.getText());
		project.setStartDate(sDate);
		project.setEndDate(eDate);
		viewSwitcher.switchTo(View.PROJECTVIEW);

	}

}

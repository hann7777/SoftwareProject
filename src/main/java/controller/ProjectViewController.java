package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Project;

public class ProjectViewController implements Initializable {

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


	private String sDateFormatted;
	private String eDateFormatted;
	private Text text;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Project p = startpageController.selectedProject;
		//initialize the info of the specific project
		LocalDate sDate = p.getStartDate();
		LocalDate eDate = p.getEndDate();
		sDateFormatted = sDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		eDateFormatted = eDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		text = new Text(p.getDescription());

		
		projectName.setText(p.getName());
		startDate.setText(sDateFormatted);
		endDate.setText(eDateFormatted);
		description.getChildren().add(text);
	}

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
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class createProjectViewController {

    @FXML
    private DatePicker endDate;

    @FXML
    private ListView<?> listviewOfDevelopers;

    @FXML
    private TextField projectDescription;

    @FXML
    private TextField projectName;

    @FXML
    private DatePicker startDate;

}
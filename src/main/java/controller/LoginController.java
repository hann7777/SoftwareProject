package controller;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField logInField;

    @FXML
    private Button loginButton;
    
    @FXML
    void onLogin(ActionEvent event) {
    	viewSwitcher.switchTo(View.STARTPAGE);
    }

    
}
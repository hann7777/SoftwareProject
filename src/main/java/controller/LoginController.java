package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Developers;
import model.User;

public class LoginController implements Initializable {

	@FXML
	private TextField logInField;

	@FXML
	private Button loginButton;

	@FXML
	void onLogin(ActionEvent event) {

		if (checkInitials(logInField.getText())) {
			viewSwitcher.switchTo(View.STARTPAGE);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		User abas = new User(false, "abas", "Abas Ali");

	}

	public boolean checkInitials(String initials) {
		if (initials.length() > 4) {
			return false;
		} 
		
		
		
		else  {
			for(User user : Developers.developers) {
				if(user.getInitials().equals(initials)) {
					return true;
				}
				
			}
			

		}

		return false;
	}

}
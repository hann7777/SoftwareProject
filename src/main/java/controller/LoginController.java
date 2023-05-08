//Jon
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.viewSwitcher;
import application.viewSwitcher.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Library;
import model.User;

public class LoginController {

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

	public boolean checkInitials(String initials) {
		if (initials.length() > 4) {
			return false;
		}

		else {
			for (User user : Main.library.getDevelopers()) {
				if (user.getInitials().equals(initials)) {
					user.setLoggedIn(true);
					return true;
				}

			}

		}

		return false;
	}

}
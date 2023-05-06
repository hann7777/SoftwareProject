package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = viewSwitcher.root;
    	root = FXMLLoader.load((getClass().getResource("/application/Login.fxml")));
		var scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    	viewSwitcher.setScene(scene);
		stage.setScene(scene);
		stage.setResizable(false);
        stage.show();
        
      
        User abas = new User(true, "abas", "Abas Ali");
    	User saeed = new User(true, "saee", "Saaed Amin");
        User jon = new User(false, "jon", "jon");

  
    }	
    
    public static void main(String[] args) {
        launch(args);
    }

}


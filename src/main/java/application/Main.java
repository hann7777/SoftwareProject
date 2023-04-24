package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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
  
    }	
    
    public static void main(String[] args) {
        launch(args);
    }

}


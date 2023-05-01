package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class viewSwitcher {
	private static Scene scene;
	public static Parent root;
	
	public static void setScene(Scene scene) {
		viewSwitcher.scene = scene;
	}

	public enum View{
		LOGIN("/application/Login.fxml"),
		STARTPAGE("/application/startpage.fxml"),
		CREATEPROJECTVIEW("/application/createProjectView.fxml"),
		PROJECTVIEW("/application/projectView.fxml"),
		CREATEACTIVITYVIEW("/application/createActivityView.fxml"),
		ACTIVITYVIEW("/application/activityView.fxml");

		
		
		private String filename;

		View(String filename) {
			
		this.filename = filename;
		}
		
		public String getFilename() {
			return this.filename;
		}
		
	}
	
	public static void switchTo(View view) {
		if(scene == null) {
			return;
		}
    	try {
			 root = FXMLLoader.load(viewSwitcher.class.getResource(view.getFilename()));
			scene.setRoot(root);
			
		} catch(IOException e) {
			e.printStackTrace();
		}

		
	}
	

}

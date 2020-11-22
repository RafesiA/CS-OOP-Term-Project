package gui;

import Refrigerator.Refrigerator;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class guiLauncher extends Application{
	
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader();
		loader.getController();
		Parent root = FXMLLoader.load(getClass().getResource("prototype.fxml"));
        Scene scene = new Scene(root);

        
		primaryStage.setScene(scene);
		primaryStage.setTitle("Refrigerator");
		primaryStage.show();
	}
	
}
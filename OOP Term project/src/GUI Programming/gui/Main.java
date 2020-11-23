package gui;


import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader();
		loader.getController();
		Parent root = FXMLLoader.load(getClass().getResource("prototype.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Refrigerator");
		primaryStage.show();
	}
	
}
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Refrigerator.Refrigerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import management.FoodTable;

public class MainController implements Initializable{
	@FXML
	Button manageButton;
	
	@FXML
	Label temp;
	
	Refrigerator ref = new Refrigerator();
	double temperature = 0.0;
	
	Timeline timeline;
	
	@FXML
	public void managementWindow(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		Parent root = FXMLLoader.load(FoodTable.class.getResource("\\Management.fxml"));
		loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setResizable(false);
		
		Stage currentStage = (Stage) manageButton.getScene().getWindow();
		currentStage.close();
		timeline.stop();
		stage.setScene(scene);
		stage.setTitle("Refrigerator Management System");
		stage.show();
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		currentTemp();
	}
	
	public void currentTemp() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(2.0), e ->{
			String text = "Temperature: " + String.format("%.1f", temperature) + "\u2103";
			temperature = ref.upTemp();
			temp.setText(text);
			System.out.println(text);
			if(temperature >= 20.0) {
				System.out.println("Temperature goes up.");
				temp.setText("Chilling..");
				ref.autoMaintain(temperature);
			}
		}));
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.play();
	}
}

package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Refrigerator.Refrigerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import management.FoodTable;

public class MainController implements Initializable{
	@FXML
	Button manageButton;
    @FXML
    private ToggleGroup type;
	
	@FXML
	Label temp;
	
    @FXML
    private Menu provideMenu;

    @FXML
    private MenuItem iceItem;

    @FXML
    private MenuItem waterItem;
    
    @FXML
    private MenuItem openFoodList;
    
    @FXML
    private MenuItem saveFoodList;
	
	Refrigerator ref = new Refrigerator();
	double temperature = 0.0;
	
	Timeline timeline;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		currentTemp();
	}
	
	
	
	@FXML
	public void managementWindow(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		Parent root = FXMLLoader.load(FoodTable.class.getResource("Management.fxml"));
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
	
	@FXML
	public void productIceMenu(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		Parent root = FXMLLoader.load(FoodTable.class.getResource("IceProduct.fxml"));
		loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setResizable(false);
		
		stage.setScene(scene);
		stage.setTitle("Product Ice");
		stage.show();
	}
	
	
    @FXML
    public void productWaterMenu(ActionEvent event) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
		Parent root = FXMLLoader.load(FoodTable.class.getResource("Water.fxml"));
		loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setResizable(false);
		
		stage.setScene(scene);
		stage.setTitle("Water Purifier System");
		stage.show();
    }
	
	public void currentTemp() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(2.0), e ->{
			String text = "Temperature: " + String.format("%.1f", temperature) + "\u2103";
			temperature = ref.upTemp();
			temp.setText(text);
			if(temperature >= 20.0) {
				temp.setText("Chilling..");
				ref.autoMaintain(temperature);
			}
		}));
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.play();
	}
	
	@FXML
	public void openFoodList() {
		try {
			ref.openFoodList();
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Saved List", ButtonType.OK);
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK)
				alert.close();
			
		} catch (IOException e) {
			Alert error = new Alert(AlertType.ERROR, "Failed open file.", ButtonType.OK);
			error.showAndWait();
			if(error.getResult() == ButtonType.OK)
				error.close();
			
		}
	}
	
	
	
	@FXML
	public void saveFoodList() {
		try {
			ref.saveFoodList();
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Saved List", ButtonType.OK);
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK)
				alert.close();
			
		} catch (IOException e) {
			Alert error = new Alert(AlertType.ERROR, "Failed save", ButtonType.OK);
			error.showAndWait();
			if(error.getResult() == ButtonType.OK)
				error.close();
		}
	}
	
	@FXML
	public void close() {
		Alert alert = new Alert(AlertType.INFORMATION, "Exit Program?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		
		if(alert.getResult() == ButtonType.YES) {
			System.exit(0);
		}
		
		if(alert.getResult() == ButtonType.NO) {
			return;
		}
		
	}
	
	
}

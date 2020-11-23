package management;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;



public class AddNewFoodController implements Initializable { 
	ObservableList<String> list = FXCollections.observableArrayList("Meat", "Vegitable", "Others");

	@FXML
	private TextField nameField;
	
	@FXML
	private TextField dateField;
	
    @FXML
    private ComboBox<String> typeBox;
    
    @FXML
    private Button acceptButton;
    @FXML
    public Button closeButton;
    
    @Override
    public void initialize(URL Location, ResourceBundle resources) {
    	typeBox.setItems(list);
    }
	
    @FXML
	public void addNewFood(ActionEvent event) {
		if(!nameField.getText().trim().isEmpty() && dateField.getText().trim().length() == 10
				&& !typeBox.getSelectionModel().isEmpty()) {
			FoodTable foodTable = new FoodTable();
    	
			foodTable.setName(nameField.getText());
			foodTable.setDate(dateField.getText());
			foodTable.setType(typeBox.getValue().toString());
    	
			TableController.myList.add(new FoodTable(foodTable.getName(), foodTable.getDate(), foodTable.getType()));
    	
			Stage stage = (Stage) acceptButton.getScene().getWindow();
			stage.close();
			
			return;
			
		}
		
		if(nameField.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "Food name must required.", ButtonType.OK);
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK) {
				alert.close();
			}
		}
		if(dateField.getText().trim().length() != 6) {
			Alert dateAlert = new Alert(AlertType.ERROR, "Date format is wrong.", ButtonType.OK);
			dateAlert.showAndWait();
			
		if(dateAlert.getResult() == ButtonType.OK)
				dateAlert.close();
		}
		if(typeBox.getSelectionModel().isEmpty()) {
			Alert typeAlert = new Alert(AlertType.ERROR, "Choose food type.", ButtonType.OK);
			typeAlert.showAndWait();
			
			if(typeAlert.getResult() == ButtonType.OK)
				typeAlert.close();
		}
	}

	@FXML
	public void close() throws Exception {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	

}

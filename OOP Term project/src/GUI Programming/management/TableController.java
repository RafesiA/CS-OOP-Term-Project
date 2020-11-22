package management;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Refrigerator.Refrigerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TableController implements Initializable {
    @FXML
    public Button addButton;
    
    @FXML
    public TextField dateField;
    
    @FXML
    public ComboBox<String> typeBox;
    
    @FXML TableView<FoodTable> tableView;
	@FXML TableColumn<FoodTable, String> foodNameColumn;
	@FXML TableColumn<FoodTable, String> foodExpiryColumn;
	@FXML TableColumn<FoodTable, String> foodTypeColumn;

	@FXML
	private Button backToMenu;   
	
	@FXML
	private CheckBox editMode;
	// Table
    static ObservableList<FoodTable> myList = FXCollections.observableArrayList();
    AddNewFoodController anf = new AddNewFoodController();
    
    Refrigerator ref = new Refrigerator();
    double currentTemp = 10.0;
    

	 @Override
	   public void initialize(URL location, ResourceBundle resources) {
	    	foodNameColumn.setCellValueFactory(new PropertyValueFactory<FoodTable, String>("name"));
	    	foodExpiryColumn.setCellValueFactory(new PropertyValueFactory<FoodTable, String>("date"));
	    	foodTypeColumn.setCellValueFactory(new PropertyValueFactory<FoodTable, String>("type"));
	    	tableView.setItems(myList);
	    	
	    	tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
	    		@Override
	    		public void handle(MouseEvent event) {
	    			if(event.getButton().equals(MouseButton.PRIMARY)) {
	    				if(event.getClickCount() == 2) {
	    					FXMLLoader loader = new FXMLLoader();
	    					loader.setLocation(getClass().getResource("FoodInfo.fxml"));
	    					try {
	    						loader.load();
	    					} catch(IOException e) {
	    						Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
	    					}
	    					InfoDialog info = loader.getController();
	    					// need Exception Handle
	    					info.setData(tableView.getSelectionModel().getSelectedItem().getName(),
	    								tableView.getSelectionModel().getSelectedItem().getDate(),
	    								tableView.getSelectionModel().getSelectedItem().getType());
	    					Parent p = loader.getRoot();
	    					Stage stage = new Stage();
	    					stage.setScene(new Scene(p));
	    					stage.show();
	    				}
	    			}
	    			
	    		}
	    	});
	    	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30.0), e ->{
	    		Alert alert = new Alert(AlertType.INFORMATION, "It's been 30 seconds since the door opened.",  ButtonType.OK);
	    		if(alert.getResult() == ButtonType.OK) {
	    			alert.close();
	    		}
	    	
	    	}));
	    	timeline.setCycleCount(Timeline.INDEFINITE);
	    	timeline.play();
	    }
	 
	 @FXML
	 public void removeFood(ActionEvent event) {
		 ObservableList<FoodTable> allFood, singleFoodRemove;
		 try {
		 allFood = tableView.getItems();
		 singleFoodRemove = tableView.getSelectionModel().getSelectedItems();
		 singleFoodRemove.forEach(allFood::remove);
		 
		 } catch(NoSuchElementException e) {
			 Alert alert = new Alert(AlertType.CONFIRMATION, "The space is empty.", ButtonType.OK);
			 alert.showAndWait();
			 if(alert.getResult() == ButtonType.OK)
				 alert.close();
		 }
		 
	 }
	 
	 
	   @FXML
	   public void openAdd(ActionEvent event) throws Exception {

			FXMLLoader loader = new FXMLLoader();
			Parent root = FXMLLoader.load(getClass().getResource("addNewFood.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			loader.getController();
			
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Refrigerator Management System");
			stage.show();
	    }
	   
	   public void changeFoodNameCellEvent(CellEditEvent editCell) {
		   FoodTable changeFood = tableView.getSelectionModel().getSelectedItem();
		   changeFood.setName(editCell.getNewValue().toString());
	   }

	   public void changeFoodDateCellEvent(CellEditEvent editCell) {
		   FoodTable changeFood = tableView.getSelectionModel().getSelectedItem();
		   changeFood.setDate(editCell.getNewValue().toString());
	   }
	   
	   @FXML
	   public void onEdit(ActionEvent event) {
		   if(editMode.isSelected()) {
			   tableView.setEditable(true);
			   foodNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			   foodExpiryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		   }
		   else {
			   tableView.setEditable(false);
		   }
	   }
	

		@FXML
		public void closeManagement() throws Exception {
			Stage closeManagement = (Stage) backToMenu.getScene().getWindow();
			closeManagement.close();
			
			loadMenu();
			
		}
		
		public void loadMenu() throws Exception {
			FXMLLoader loader = new FXMLLoader();
			loader.getController();
			Parent root = FXMLLoader.load(getClass().getResource("prototype.fxml"));
	        Scene scene = new Scene(root);
	        Stage primaryStage = new Stage();
	        
	        primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Refrigerator");
			primaryStage.show();
		}
}

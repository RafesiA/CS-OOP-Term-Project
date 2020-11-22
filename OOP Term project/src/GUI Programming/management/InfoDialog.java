package management;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InfoDialog implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField dateField;

    @FXML
    private Button acceptButton;

    @FXML
    private TextField typeField;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	// TODO
    }
    

    @FXML
    public void OKButton(ActionEvent event) {
    	Stage stage = (Stage) acceptButton.getScene().getWindow();
    	stage.close();
    }
    
    public void setData(String name, String date, String type) {
    	nameField.setText(name);
    	dateField.setText(date);
    	typeField.setText(type);
    }


    }


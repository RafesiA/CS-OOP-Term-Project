package management;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class IceManagement implements Initializable{

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;
    
    @FXML
    private Button takeButton;

    
    public void ProgressThread() {
    	Task<Void> task = new Task<Void>() {
    		@Override
    		protected Void call() throws Exception{
    			File file = new File("C:\\Windows");
    			File[] listOfFile = file.listFiles();
    			
    			for(int i = 0; i < listOfFile.length; i++) {
    				updateProgress(i, listOfFile.length);
    				updateMessage(listOfFile[i].getName());
    				Thread.sleep(100);
    			}
    			return null;
    		}
    	};
    	
    	task.messageProperty().addListener(new ChangeListener<String>() {
    		@Override
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			progressLabel.setText("Producting Ice...");
    		}
    	});
    	
    	task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
    		@Override
    		public void handle(WorkerStateEvent event) {
    			progressLabel.setText("Completed!");
    			takeButton.setDisable(false);
    			
    		}
    	});
    	
    	progressBar.progressProperty().bind(task.progressProperty());
    	
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        
    }
    
    @FXML
    void closeWindow(ActionEvent event) {
    	Stage stage = (Stage) takeButton.getScene().getWindow();
    	stage.close();
    }
    
    

    
    
   @Override
   public void initialize(URL url, ResourceBundle rb) {
	   ProgressThread();
   }
    
}

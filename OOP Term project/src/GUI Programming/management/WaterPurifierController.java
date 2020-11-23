package management;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;

import Refrigerator.WaterPurifRef;

public class WaterPurifierController implements Initializable {

    @FXML
    private Label tempStatus;

    @FXML
    private ToggleGroup type;

    @FXML
    private RadioButton warmButton;

    @FXML
    private RadioButton coldButton;

    @FXML
    private RadioButton hotButton;

    @FXML
    private Button button;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label message;
    
    @FXML
    private Label caution;
    
    WaterPurifRef ref = new WaterPurifRef();
    Timeline timeline;
    double temperature = 80.0;
    
    Task<Void> task;
    Thread th;
    
    @FXML
    public void productWater(ActionEvent event) {
    	if(button.getText().equals("Push")) {
        	button.setText("Pull");
        	System.out.println("event");
        	provide();
        	return;
    	}
    	if(button.getText().equals("Pull")) {
    		task.cancel();
			warmButton.setDisable(false);
			coldButton.setDisable(false);
			hotButton.setDisable(false);
    		button.setText("Push");
    		return;
    	}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	currentTemp();
    	progressBar.setProgress(0);
    	
    }
	public void currentTemp() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(2.0), e ->{
			temperature += ref.boil();
			String text = String.format("%.1f", temperature) + "\u2103";
			tempStatus.setText(text);
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	@FXML
	public void radioCheck(ActionEvent event) {
		if(warmButton.isSelected()) {
			message.setText("You selected warm water.");
			caution.setVisible(false);
			progressBar.setStyle("-fx-accent: orange;");
		}
		if(coldButton.isSelected()) {
			message.setText("You selected cold water.");
			caution.setVisible(false);
			progressBar.setStyle("-fx-accent: blue;");
		}
		if(hotButton.isSelected()) {
			message.setText("You selected hot water.");
			caution.setVisible(true);
			progressBar.setStyle("-fx-accent: red;");
		}
	}
	
	public void provide() {
    	task = new Task<Void>() {
    		@Override
    		public Void call() throws Exception{
    			for(int i = 1; i <= 100; i++) {
    				if(isCancelled()) {
    					updateMessage("Pull");
    					break;
    				}
    				updateProgress(i, 100);
    				try {
    					Thread.sleep(10);
    				} catch(InterruptedException e) {
    					if(isCancelled()) {
    						updateMessage("pull");
    						break;
    					}
    				}
    			}
    			return null;
    		}
    	};
    	
    	task.setOnRunning(new EventHandler<WorkerStateEvent>() {
    		@Override
    		public void handle(WorkerStateEvent event) {
    			warmButton.setDisable(true);
    			coldButton.setDisable(true);
    			hotButton.setDisable(true);
    		}
    	});
    	
    	task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
    		@Override
    		public void handle(WorkerStateEvent event) {
    			
    		}
    	});
    	
    	progressBar.progressProperty().unbind();
    	progressBar.progressProperty().bind(task.progressProperty());
    	th = new Thread(task);
    	th.setDaemon(true);
    	th.start();
	}
}
	

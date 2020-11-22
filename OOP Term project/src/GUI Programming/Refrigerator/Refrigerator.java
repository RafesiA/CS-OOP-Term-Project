package Refrigerator;

import java.util.Random;

public class Refrigerator {
	// deleted related about food status. and created new Food class.
	public double temperature;
	public boolean switchButton;		//switch -> switchButton
	public int expiryDate;
	public double smellSensor;
	public String signal = "You leaved door.";			// warning message
	final int coldroom = 10;			// this will be ice storage i.g) freezing water
	public int freezer;
	
	public double upTemp() {
		Random rand = new Random();
		double randomValue = -2.0 + (2.0 - -2.0) * rand.nextDouble();
		return temperature += randomValue;
	}
	
	// this function will when user open and close door, 
	public double autoMaintain(double temperature) {
		this.temperature = 3.0;
		return temperature;
	}
	
	
	public void sensorSystem() {
		// REF smell will change randomly and this system detect that and announce to user.
	}
	
	
	
}

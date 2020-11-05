package Refrigerator;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class Refrigerator {
	// deleted related about food status. and created new Food class.
	public double temperature;
	public boolean switchButton;		//switch -> switchButton
	public int expiryDate;
	public double smellSensor;
	final int storage = 100; 		// merged storage meat and vegetable
	public String signal = "You leaved door.";			// warning message
	final int coldroom = 10;			// this will be ice storage i.g) freezing water
	public int freezer;				
	
	ArrayList<Food> food = new ArrayList<Food>();
	
	public void init() {
		this.temperature = -10.0;
		this.switchButton = false;
		this.smellSensor = 0.0;
		this.freezer = 10;
	}
	
	public boolean open() {
		return this.switchButton = true;
	}
	
	public boolean close() {
		return this.switchButton = false;
	}
	
	
	public void inputNewFood(Scanner nScan, Scanner dScan) {
		System.out.println("input food name");
		String name = nScan.nextLine();
		String dateCheck;
		
		System.out.println("input expiry date. ex)201001");			// have to add exception date
		try {
			int date = dScan.nextInt();
			dateCheck = Integer.toString(date);
			if(dateCheck.length() == 6)
				food.add(new Food(name, date));
			
			else System.out.println("Wrong date format.");
			
			
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println("input numbers.");
			dScan.close();
		}
	}
	
	// added more function : delete food.
	public void deleteFood() {
		if(!food.isEmpty()) {
			System.out.println("Choose one what you want delete food number.");
			displayMessage();
			Scanner scan = new Scanner(System.in);
			int select = scan.nextInt();
			try {
				food.remove(select - 1);
				System.out.println("Successfully deleted from refrigerator.");
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.out.println("You chosen wrong number.");
			}
		}
		else {
			System.out.println("Stoarge is empty.");
		}
		
	}
	// show currentTemp
	public void currentTemp() {
		System.out.println("Current temperatur is : " + temperature);
	}
	
	// this function will when user open and close door, 
	public double autoMaintain(double freezer) {
		freezer = -10.0;
		return freezer;
	}
	
	/* included inputNewFood(), deleted this.
	public int inputExDate(Scanner scan) {
		System.out.println("input expiry date.");
		expiryDate = scan.nextInt();
		return expiryDate;
	}
	*/
	
	public void displayMessage() {
		if(!food.isEmpty()) {
			for(int i = 0; i<food.size(); i++) {
				String str = Integer.toString(food.get(i).getExDate());
				System.out.println("No. " + (i + 1) + " " + food.get(i).getFoodName());
				System.out.println("Expiry Date : " + "20" +  str.substring(0, 2) + "³â" + 
					str.substring(2, 4) + "¿ù" + str.substring(4, 6) + "ÀÏ");
			}
		} else {
			System.out.println("Empty.\n");
		}
	}
	
	public void warningSound() {
		//if door opened and user leaved this 30 seconds.
		System.out.println(signal);
	}
	
	public void sensorSystem() {
		// REF smell will change randomly and this system detect that and announce to user.
	}
	
	
	
}

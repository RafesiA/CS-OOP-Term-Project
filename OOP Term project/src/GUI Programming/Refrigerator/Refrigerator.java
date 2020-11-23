package Refrigerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

import management.FoodTable;
import management.TableController;

public class Refrigerator {
	
	public double temperature;
	
	String[] splitedStr = null;
	
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
	
	public void openFoodList() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Test\\out.txt")));
		String line = null;
		
		while((line = br.readLine()) != null) {
			splitedStr = null;
			splitedStr = line.split("\t");
			
			for(int i=0;i<splitedStr.length;i++) {
				splitedStr[i] = splitedStr[i].trim();
			}
			TableController.myList.add(new FoodTable(splitedStr[0], splitedStr[1], splitedStr[2]));
		}
		br.close();
	}
	
	public void saveFoodList()  throws IOException{
		PrintWriter pw = new PrintWriter("D:\\Test\\out.txt");
		
		for(int i =0; i<TableController.myList.size(); i++) {
			String dataName = TableController.myList.get(i).getName();
			String dataDate = TableController.myList.get(i).getDate();
			String dataType = TableController.myList.get(i).getType();
			
			pw.print(dataName + "\t");
			pw.print(dataDate + "\t");
			pw.print(dataType + "\n");
			
		}
	pw.close();
		
	}
	
	
}

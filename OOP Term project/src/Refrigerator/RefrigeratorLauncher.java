package Refrigerator;

import java.util.Scanner;

public class RefrigeratorLauncher {
	public static void main(String[] args) {
		int i;
		Refrigerator ref = new Refrigerator();
		ref.init();
		System.out.println("Refrigerator prototype.\n");
		
		
		while(true) {
			Scanner initScan = new Scanner(System.in);
			Scanner scan = new Scanner(System.in);
			Scanner foodNameScanner = new Scanner(System.in);
			Scanner foodDateScanner = new Scanner(System.in);
			System.out.println("Open door to start manage refrigerator.");
			String init = initScan.nextLine();
	

			if(init.equals("open") || init.equals("Open")) {
				ref.open();
				
				while(true) {
					System.out.println("\nSelect Menu");
					System.out.println("1. Input new food.");
					System.out.println("2. Display all food");
					System.out.println("3. Current Temperatur");
					System.out.println("4. Delete food");
					System.out.println("5. Exit");
					i = scan.nextInt();
					
					switch(i) {
						case 1:
							ref.inputNewFood(foodNameScanner, foodDateScanner);
							continue;
						case 2:
							ref.displayMessage();
							continue;
						case 3:
							ref.currentTemp();
							continue;
						case 4:
							ref.deleteFood();
							continue;
						case 5:
							System.out.println("You closed Ref.");
							ref.close();
							break;
						}
					break;
					}
				}
			else if(!init.equals("open") || !init.equals("Open")) {
				System.out.println("Door still closed.");
			}
		}
		
	}
}

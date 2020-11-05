package Refrigerator;

public class Food {
	private String name;
	private int expiryDate;
	
	public Food(String name, int date) {
		this.name = name;
		this.expiryDate = date;
		
	}
	
	public String getFoodName() {
		return name;
	}
	
	public int getExDate() {
		return expiryDate;
	}
}

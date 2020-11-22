package management;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FoodTable {
	private StringProperty name;
	private StringProperty date;
	private StringProperty type;
	
	public FoodTable(String name, String date, String type) {
		this.name = new SimpleStringProperty(name);
		this.date = new SimpleStringProperty(date);
		this.type = new SimpleStringProperty(type);
		
	}
	public FoodTable() {
		
	}


	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public String getDate() {
		return date.get();
	}
	
	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}
	
	public String getType() {
		return type.get();
	}
	
	public void setType(String type) {
		this.type = new SimpleStringProperty(type);
	}
}

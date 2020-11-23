package Refrigerator;

import java.util.Random;

public class WaterPurifRef extends Refrigerator {
	
	
	public WaterPurifRef() {
		super();
		
	}
	
	public double boil() {
		Random rand = new Random();
		double maintain = -0.3 + (0.3 - -0.3) * rand.nextDouble();
		return maintain;
	}
	
}

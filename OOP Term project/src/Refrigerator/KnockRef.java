package Refrigerator;

public class KnockRef extends Refrigerator {
	private String homeBarDisplay;
	private boolean impactSensor;
	private boolean brightness;
	private boolean transparent;
	
	public KnockRef() {
		super();
	}
	
	public void recogniseImpact() {
		// when user knocking homebar, birghtness set false.
		this.brightness = false;
	}
}

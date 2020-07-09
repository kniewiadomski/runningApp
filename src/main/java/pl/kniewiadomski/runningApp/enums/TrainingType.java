package pl.kniewiadomski.runningApp.enums;

public enum TrainingType {
	CONSTANT("Ciągły"),
	TIME_INTERVAL("Interwał czasowy"),
	DISTANCE_INTERVAL("Interwał dystansowy");
	
	private final String displayValue;
	
	private TrainingType(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}

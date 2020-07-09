package pl.kniewiadomski.runningApp.enums;

public enum GoalType {
	QUANTITY("Ilość treningów"),
	TOTAL_DISTANCE("Całkowity dystans"),
	DISTANCE("Jednorazowy dystans"),
	TIME_ON_DISTANCE("Czas na dystansie");
	
	private final String displayValue;
	
	private GoalType(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}

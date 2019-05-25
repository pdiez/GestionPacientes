package model;

public class Sensor {
	
	private String id;
	private String sensorType;
	private int currentValue;
	private int alertValue;
	private int maxValue;
	private int minValue;
	private int userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public int getAlertValue() {
		return alertValue;
	}

	public void setAlertValue(int alertValue) {
		this.alertValue = alertValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Sensor() {}
	
	@Override	
	public String toString() {
		return this.id;
	}
	
}

package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sensor {
	
	private String id;
	private String sensorType;
	private int currentValue;
	private int alertValue;
	private int maxValue;
	private int minValue;
	private String user;
	


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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Sensor() {}
	
	public Sensor(String id, String sensorType) {
		this.id = id;
		this.sensorType = sensorType;
	}
	
	
	@Override	
	public String toString() {
		return this.id;
	}
	
}

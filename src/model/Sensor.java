package model;

import util.Persistent;

public class Sensor {
	
	private int id;
	private String sensorType;
	private int sensorTypeId;
	private int alertValue;
	private int userId;
	private int active;
	private int currentValue;
	private int maxValue;

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}
	
	public int getSensorTypeId() {
		return sensorTypeId;
	}

	public void setSensorTypeId(int sensorTypeId) {
		this.sensorTypeId = sensorTypeId;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	
	public int getAlertValue() {
		return alertValue;
	}

	public void setAlertValue(int alertValue) {
		this.alertValue = alertValue;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Sensor() {}
	
	
	
	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	@Override	
	public String toString() {
		return this.sensorType + " nº " + this.id;
	}

	public void actualizarDatos() {
		this.currentValue = Persistent.getSensorLastValue(this);
		this.maxValue = Persistent.getSensorMaxValueTPA(this);
	}

	public void cambiarEstado() {
		this.active = this.active == 1 ? 0 : 1;
		this.update();
		
	}

	private void update() {
		Persistent.updateSensor(this);		
	}

	public void Guardar() {
		Persistent.saveSensor(this);
		
	}

	public void Borrar() {
		
		Persistent.deleteSensor(this);
	}
	
}

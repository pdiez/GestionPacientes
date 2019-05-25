package model;

import util.Persistent;

public class Message {
	
	
	private int from;
	private int to;
	private java.util.Date date;
	private String text;
	
	
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Message() {
	}
	
	public void Guardar() {
		Persistent.saveMessage(this);
	}

}

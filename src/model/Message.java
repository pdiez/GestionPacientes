package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import util.Persistent;

@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
	
	private String from;
	private String to;
	private String date;
	private String text;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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

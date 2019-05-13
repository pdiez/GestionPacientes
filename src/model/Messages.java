package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="chat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Messages {
	
	@XmlElement(name="message")
	private List<Message> messageList;

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messages) {
		this.messageList = messages;
	}
	
	public Messages() {}
	
		
}

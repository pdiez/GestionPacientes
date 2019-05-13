package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sensors")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sensors {
	@XmlElement(name="sensor")
	private List<Sensor> sensorList;

	public List<Sensor> getSensorList() {
		return sensorList;
	}
	
	public List<Sensor> getSensorbyUser(String user) {
		List<Sensor> s = new ArrayList<Sensor>();
		for (Sensor n : sensorList) {
			if (n.getUser().equals(user)) {
				s.add(n);
			}
		}
		return s;
	}


	public void setSensorList(List<Sensor> sensors) {
		this.sensorList = sensors;
	}
	
	public Sensors() {
		sensorList = new ArrayList<Sensor>();
	}
}

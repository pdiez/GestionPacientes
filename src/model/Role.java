package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Role {
	
	private int id;
	private String roleName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Role() {}
	public Role(int id, String name) {
		this.id = id;
		this.roleName=name;
	}
	
	@Override	
	public String toString() {
		return this.roleName;
	}

}

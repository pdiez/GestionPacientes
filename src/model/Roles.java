package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="roles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Roles {
	
	@XmlElement(name="role")
	private List<Role> roleList;

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roles) {
		this.roleList = roles;
	}
	
	public Roles() {
		roleList = new ArrayList<Role>();
	}
	

}

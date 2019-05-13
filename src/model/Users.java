package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
	
	@XmlElement(name="user")
	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public List<User> getPacientes() {
		List<User> u = new ArrayList<User>();
		for (User n : userList) {
			if (n.getRole()==1) {
				u.add(n);
			}
		}
		return u;
	}
	public void setUserList(List<User> users) {
		this.userList = users;
	}
	
	public Users() {
		userList = new ArrayList<User>();
	}
	

}

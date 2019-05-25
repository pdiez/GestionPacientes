package model;

import util.Persistent;

public class User {
	
	private int id;
	private String username;
	private String password;
	private int role;
	private String name;
	private String phone;
	private String notes;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public User() {
	}

	public User(String u) {
		this.username = u;
	}

	@Override
	public String toString() {
		return this.username;
	}
	
	public void Guardar() {
		Persistent.saveUser(this);
	}
	
	public void Eliminar() {
		Persistent.delUser(this);
	}

}

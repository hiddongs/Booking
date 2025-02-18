package com.booking.member;

public class User {

	String ID;
	String email;
	String passwd;
	String name;
	
	

	public User(String iD, String email, String passwd, String name) {
		super();
		ID = iD;
		this.email = email;
		this.passwd = passwd;
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

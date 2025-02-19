package com.booking.member;

import java.util.Date;

public class User {

	String ID;
	String email;
	String passwd;
	String name;
	Enum Grade;
	
	int point;
	int cash;
	Date reg_date;
	public User(String iD, String email, String passwd, String name, Enum grade, int point, int cash, Date reg_date) {
		super();
		ID = iD;
		this.email = email;
		this.passwd = passwd;
		this.name = name;
		Grade = grade;
		this.point = point;
		this.cash = cash;
		this.reg_date = reg_date;
	}
	

	
	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public int getCash() {
		return cash;
	}


	public void setCash(int cash) {
		this.cash = cash;
	}


	public Date getReg_date() {
		return reg_date;
	}


	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}


	


	public Enum getGrade() {
		return Grade;
	}


	public void setGrade(Enum grade) {
		Grade = grade;
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

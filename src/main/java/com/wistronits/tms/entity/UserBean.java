package com.wistronits.tms.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserBean {
	private int id;
	private String username;
	private String password;
	private String mail;
	private String telphone;
	private int permission;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getDate() {
		String desc = "";
		if (this.date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			desc = sdf.format(this.date);
		}
		return desc;
		//return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	

}

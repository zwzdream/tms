package com.wistronits.tms.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserBean {
	private int id;
	private String username;
	private String password;
	private String email;
	private String telephone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			desc = sdf.format(this.date);
		}
		return desc;
		//return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	

}

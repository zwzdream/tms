package com.wistronits.tms.entity;

import java.io.Serializable;
import java.util.Date;


public class ResumeBean implements Serializable {
		
	private int id;
	private String firstName;
	private String lastName;
	private Date birth;
	private String gender;
	private String mobile;
	private Date starts;
	private String email;
	private String residency;
	private String education;
	private String workExp;
	private String projectExp;
	 
	
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getWorkExp() {
		return workExp;
	}
	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}
	public String getProjectExp() {
		return projectExp;
	}
	public void setProjectExp(String projectExp) {
		this.projectExp = projectExp;
	}
	
	public String getResidency() {
		return residency;
	}
	public void setResidency(String residency) {
		this.residency = residency;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getStarts() {
		return starts;
	}
	public void setStarts(Date starts) {
		this.starts = starts;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

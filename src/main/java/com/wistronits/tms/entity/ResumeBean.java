package com.wistronits.tms.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ResumeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private Date birth;
	private Boolean gender;
	private String mobile;
	private Date starts;
	private String email;
	private String filePath;
	private String fileName;
	private String residency;
	private String education;
	private String workExp;
	private String projectExp;
	private int age;
	private Date lastMTime;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName() {
		String[] temp= this.filePath.split("\\\\");
		this.fileName = temp[temp.length-1];
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	 
	
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
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
		setAge();
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
	public int getAge() {
		return age;
	}
	public void setAge() {
		this.age = getAgeFromBirth();
	}

	private int getAgeFromBirth(){
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(this.birth);
		
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		
		int age = yearNow-yearBirth;
		if(monthNow<=monthBirth){
			if(monthNow==monthBirth){
				if(dayOfMonthNow<dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}
	public String getLastMTime() {
		String desc = "";
		if (this.lastMTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			desc = sdf.format(this.lastMTime);
		}
		return desc;
		//return lastMTime;
	}
	public void setLastMTime(Date lastMTime) {
		this.lastMTime = lastMTime;
	}
	public String toString(){
		return this.firstName+" "+this.lastName+" "+this.mobile+" "+this.email+" "+this.residency+" "+this.education+" "+this.workExp+" "+this.projectExp;
	}
}

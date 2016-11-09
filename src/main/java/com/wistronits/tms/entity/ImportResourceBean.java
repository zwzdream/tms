package com.wistronits.tms.entity;

import java.util.Calendar;
import java.util.Date;


public class ImportResourceBean {
	private int id;
	private String firstName;
	private String lastName;
	private Date birth;
	private Boolean gender;
	private String filePath;
	private int age;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
		setAge();
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}

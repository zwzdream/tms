package com.wistronits.tms.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ImportResourceBean {
	private String firstName;
	private String lastName;
	private Date birth;
	private Boolean gender;
	private MultipartFile inputFile;
	private String filePath;
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
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public MultipartFile getInputFile() {
		return inputFile;
	}
	public void setInputFile(MultipartFile inputFile) {
		this.inputFile = inputFile;
	}
	
	
}

package com.wistronits.tms.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ResumeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String title;
	private String mobile;
	private String email;
	private String industryExperience;
	private String filePath;
	private String fileName;
	private String location;
	private Boolean relocation;
	private String degree;
	private String workEligibility;
	private String website;
	private Date addTime;
	private Date lastMTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIndustryExperience() {
		return industryExperience;
	}
	public void setIndustryExperience(String industryExperience) {
		this.industryExperience = industryExperience;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		String[] temp= this.filePath.split("\\\\");
		this.fileName = temp[temp.length-1];
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getRelocation() {
		return relocation;
	}
	public void setRelocation(Boolean relocation) {
		this.relocation = relocation;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getWorkEligibility() {
		return workEligibility;
	}
	public void setWorkEligibility(String workEligibility) {
		this.workEligibility = workEligibility;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAddTime() {
		String desc = "";
		if (this.lastMTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			desc = sdf.format(this.lastMTime);
		}
		return desc;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getLastMTime() {
		String desc = "";
		if (this.lastMTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			desc = sdf.format(this.lastMTime);
		}
		return desc;
	}
	public void setLastMTime(Date lastMTime) {
		this.lastMTime = lastMTime;
	}
	@Override
	public String toString() {
		return  name + " " + title + " " + mobile + " "+email  + " "
	+ location + " " + degree+ " " + workEligibility + " " + website ;
	}


	

	
}

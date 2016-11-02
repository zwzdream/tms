package com.wistronits.tms.entity;

import java.util.Date;

public class JDBean {

	  private int no;//系统唯一标识，自增长
	  private int priority;//优先级 0=普通，1=优先，2=紧急
	  private String title;//Job Ttitle，限定长度为50
	  private String client;//客户名，限定长度为50
	 // private int openings;//?
	  private String duration;//持续时间，单位为月
	  private String location;//工作地点，限定长度为50
	  private int status;//JD状态，0=closed，1=processing
	  private String payrate;//
	  private String description;//描述信息，限定长度为500
	  private String keyword;//JD的关键字，以空格或逗号分隔，限定长度为50
	  private String notes;//用户填写的注释，限定长度为500
	  private boolean local;//
	  private int owner;//当前处理人员
	  private String qualification;//条件
	  private Date modifydate;//修改日期
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPayrate() {
		return payrate;
	}
	public void setPayrate(String payrate) {
		this.payrate = payrate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public boolean isLocal() {
		return local;
	}
	public void setLocal(boolean local) {
		this.local = local;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	@Override
	public String toString() {
		return "JDBean [no=" + no + ", priority=" + priority + ", title=" + title + ", client=" + client + ", duration="
				+ duration + ", location=" + location + ", status=" + status + ", payrate=" + payrate + ", description="
				+ description + ", keyword=" + keyword + ", notes=" + notes + ", local=" + local + ", owner=" + owner
				+ ", qualification=" + qualification + ", modifydate=" + modifydate + "]";
	}

	  

	
	  




	}

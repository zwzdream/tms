package com.wistronits.tms.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskBean {
	private int id;
	private String name;
	private int priority;
	private int status;
	private String description;
	private Date create_time;
	private Date start_time;
	private Date complete_time;
	private String duration;
	private int creater;
	private int last_assignee;
	private String assigneeName;
	private int consummator;
	private Date last_update;
	private ArrayList<TaskNotes> taskNotes;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreate_time() {
		String rTime = "";
		if (this.create_time != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rTime = sdf.format(this.create_time);
		}
		return rTime;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getStart_time() {
		String rTime = "";
		if (this.start_time != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rTime = sdf.format(this.start_time);
		}
		return rTime;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public String getComplete_time() {
		String rTime = "";
		if (this.complete_time != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rTime = sdf.format(this.complete_time);
		}
		return rTime;
	}
	public void setComplete_time(Date complete_time) {
		this.complete_time = complete_time;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
	public int getLast_assignee() {
		return last_assignee;
	}
	public void setLast_assignee(int last_assignee) {
		this.last_assignee = last_assignee;
	}
	
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public int getConsummator() {
		return consummator;
	}
	public void setConsummator(int consummator) {
		this.consummator = consummator;
	}
	
  
	public String getLast_update() {
		String rTime = "";
		if (this.last_update != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rTime = sdf.format(this.last_update);
		}
		return rTime;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

/*	public Map<Object,Object> getTaskNotes() {
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("notes", this.taskNotes);
		return map;
	}*/
	public  ArrayList<TaskNotes> getTaskNotes() {
			return this.taskNotes;
	}
	public void setTaskNotes(ArrayList<TaskNotes> arrayList) {
		this.taskNotes = arrayList;
	}
	
	
	
	
	
	

}

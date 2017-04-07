package com.wistronits.tms.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskNotes {
	private int id;
	private Date update_time;
	private int assigneeId;
	private String assignee;
	private String note;
	private int task_id;
	private TaskBean taskBean;
	
	public TaskNotes() {
		super();
	}
	public TaskNotes(String assignee,String note) {
		super();
		this.assignee= assignee;
		this.note = note;
	}
	public String getUpdate_time() {
		String rTime = "";
		if (this.update_time != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rTime = sdf.format(this.update_time);
		}
		return rTime;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	

	public int getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public TaskBean getTaskBean() {
		return taskBean;
	}
	public void setTaskBean(TaskBean taskBean) {
		this.taskBean = taskBean;
	}
	
	
	
	

}

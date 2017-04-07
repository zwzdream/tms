package com.wistronits.tms.service;

import java.util.ArrayList;
import java.util.Date;

import com.wistronits.tms.entity.TaskBean;
import com.wistronits.tms.entity.TaskNotes;

public interface ITaskService {
	public int saveTask(TaskBean taskBean);
	public int editTask(TaskBean taskBean);
	public int assignTaskTo(TaskNotes taskNote);
	public ArrayList<TaskBean> listByKeyword(String keyword);
	public TaskBean getTaskById(int id);
	public String startTask(int id,Date start_time);
	public String completeTask(int id,Date complete_time,int uid);
	public ArrayList<TaskBean> listAll();
	public ArrayList<TaskNotes> getTaskNotesById(int id);
	public int listCurrentTask();
	public String deleteTask(int id);
}

package com.wistronits.tms.dao;

import java.util.ArrayList;
import java.util.Date;

import com.wistronits.tms.entity.TaskBean;
import com.wistronits.tms.entity.TaskNotes;



public interface ITaskDao {
	public int saveTask(TaskBean taskBean);
	public int editTaskwithAssignee(TaskBean taskBean);
	public int editTaskwithoutAssignee(TaskBean taskBean);
	public int assignTaskTo(TaskNotes taskNote);
	public ArrayList<TaskBean> listByKeyword(String keyword);
	public TaskBean getTaskById(int id);
	public int startTask(int id,Date start_time);
	public int completeTask(int id,Date complete_time);
	public ArrayList<TaskBean> listAll();
	public ArrayList<TaskNotes> getTaskNotesById(int id);
	public int listCurrentTask();
	public int deleteTask(int id);

}

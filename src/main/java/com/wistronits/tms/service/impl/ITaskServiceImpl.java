package com.wistronits.tms.service.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wistronits.tms.dao.ITaskDao;
import com.wistronits.tms.entity.TaskBean;
import com.wistronits.tms.entity.TaskNotes;
import com.wistronits.tms.service.ITaskService;



@Service("taskService")
public class ITaskServiceImpl implements ITaskService{
	
	@Resource
	private ITaskDao taskDao;

	@Override
	public int saveTask(TaskBean taskBean) {
		return taskDao.saveTask(taskBean);
	}

	@Override
	public int editTask(TaskBean taskBean) {
		if(taskDao.getTaskById(taskBean.getId()).getLast_assignee()==taskBean.getLast_assignee()){
			return taskDao.editTaskwithoutAssignee(taskBean);
		}
		return taskDao.editTaskwithAssignee(taskBean);
		
	}

	@Override
	public int assignTaskTo(TaskNotes taskNote){
		return taskDao.assignTaskTo(taskNote);
	}

	@Override
	public ArrayList<TaskBean> listByKeyword(String keyword) {
		return taskDao.listByKeyword(keyword);
	}

	@Override
	public TaskBean getTaskById(int id) {
		TaskBean taskBean= taskDao.getTaskById(id);
		ArrayList<TaskNotes> noteArr=taskBean.getTaskNotes();
		
		if(noteArr==null||noteArr.size()==0){
			
			 noteArr.add(new TaskNotes("","The task is not assigned!"));
			taskBean.setTaskNotes(noteArr);
			}
		return taskBean;
	}

	@Override
	public String startTask(int id, Date start_time) {
		int oldStatus=taskDao.getTaskById(id).getStatus();
	
		if(oldStatus==0){
			if(taskDao.startTask(id, start_time)==1){
				return "Start the task successfully!";
			}
		}else if(oldStatus==1){
			return "The task has started!";
			
		}else if(oldStatus==2){
			return "The task has been completed, unable to start again!";
		}
		return "Unknown error, please contact the administrator!";
		
	}

	@Override
	public String completeTask(int id, Date complete_time) {
		int oldStatus=taskDao.getTaskById(id).getStatus();
		
		if(oldStatus==0){
			if((taskDao.startTask(id, complete_time)==1)&(taskDao.completeTask(id, complete_time))==1){
				return "Complete the task successfully!";
			}		
		}else if(oldStatus==1){
			if(taskDao.completeTask(id, complete_time)==1){
			 return "Complete the task successfully!";
			}
		}else if(oldStatus==2){
			return "The task has been completed!";
		}
		return "Unknown error, please contact the administrator!";
		
	}

	@Override
	public ArrayList<TaskBean> listAll() {
		return taskDao.listAll();
	}
	
	@Override
	public ArrayList<TaskNotes> getTaskNotesById(int id) {
		return taskDao.getTaskNotesById(id);
	}


	@Override
	public int listCurrentWeekCount() {
		return taskDao.listCurrentWeekCount();
	}

	

}

package com.activity.model;

import java.sql.Date;
import java.util.List;

public class ActivityService {
	private ActivityDAO_Interface activityDao;
	
	public ActivityService(){
		activityDao = new ActivityDAO();
	}
	
	public Activity addActivity(String restMemId, String actName, String actContent, Date actDate, Date actFDate,
			Integer actStatus, Integer actULimit, Integer actLLimit, Integer actKind, String actAnotherKind,
			byte[] actInitImg){
			Activity activity = new Activity();
			activity.setRestMemId(restMemId);
			activity.setActName(actName);
			activity.setActContent(actContent);
			activity.setActDate(actDate);
			activity.setActFDate(actFDate);
			activity.setActStatus(actStatus);
			activity.setActULimit(actULimit);
			activity.setActLLimit(actLLimit);
			activity.setActKind(actKind);
			activity.setActAnotherKind(actAnotherKind);
			activity.setActInitImg(actInitImg);
			activityDao.add(activity);
			return activity;
		}
	
	public Activity updateActivity(Integer actNo,String restMemId, String actName, String actContent, Date actDate, Date actFDate,
			Integer actStatus, Integer actULimit, Integer actLLimit, Integer actKind, String actAnotherKind,
			byte[] actInitImg){
			Activity activity = new Activity();
			activity.setActNo(actNo);
			activity.setRestMemId(restMemId);
			activity.setActName(actName);
			activity.setActContent(actContent);
			activity.setActDate(actDate);
			activity.setActFDate(actFDate);
			activity.setActStatus(actStatus);
			activity.setActULimit(actULimit);
			activity.setActLLimit(actLLimit);
			activity.setActKind(actKind);
			activity.setActAnotherKind(actAnotherKind);
			activity.setActInitImg(actInitImg);
			activityDao.update(activity);
			return activity;
		}
	
	public void deleteActivity(Integer actNo){
		activityDao.delete(actNo);
	}
	
	public Activity getOneActivity(Integer actNo){
		return activityDao.findByPK(actNo);
	}
	
	public List<Activity> getAll(){
		return activityDao.getAll();
	}
}







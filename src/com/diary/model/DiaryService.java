package com.diary.model;

import java.sql.Timestamp;
import java.util.List;

public class DiaryService {
	
	private DiaryDAO_Interface dao;
	
	public DiaryService(){
		dao = new DiaryDAO();
	}
	
	public Diary addDia(Integer memno, String dianame,String diatext, byte[] diaimg, 
					Timestamp diacretime, Timestamp diamodtime, Integer diastate){
		Diary diary = new Diary();
		
		diary.setMemno(memno);
		diary.setDianame(dianame);
		diary.setDiatext(diatext);
		diary.setDiaimg(diaimg);
		diary.setDiacretime(diacretime);
		diary.setDiamodtime(diamodtime);
		diary.setDiastate(diastate);
		dao.insert(diary);
	
		return diary;
	}
	
	public Diary updateDia(Integer memno, String dianame,String diatext, byte[] diaimg, 
					Timestamp diacretime, Timestamp diamodtime, Integer diastate){
		
		Diary diary = new Diary();
		
		diary.setMemno(memno);
		diary.setDianame(dianame);
		diary.setDiatext(diatext);
		diary.setDiaimg(diaimg);
		diary.setDiacretime(diacretime);
		diary.setDiamodtime(diamodtime);
		diary.setDiastate(diastate);
		
		dao.update(diary);
		
		return diary;
	}
	
	public void deleteDia(Integer diano){
		dao.delete(diano);
	}
	
	
	public Diary getOneDia(Integer diano){
		return dao.findByPrimaryKey(diano);
	}
	
	
	public List<Diary> getAll(){
		return dao.getAll();
	}
	

}

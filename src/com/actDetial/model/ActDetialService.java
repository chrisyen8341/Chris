package com.actDetial.model;

import java.util.List;

public class ActDetialService {
	private ActDetialDAO_Interface actDetialDao;
	
	public ActDetialService (){
		actDetialDao = new ActDetialDAO();
	}
	
	public ActDetial addActDetial(Integer actNo, Integer memNo, Integer memActStatus){
		ActDetial actDetial = new ActDetial();
		actDetial.setActNo(actNo);
		actDetial.setMemNo(memNo);
		actDetial.setMemActStatus(memActStatus);
		actDetialDao.add(actDetial);
		return actDetial;
	}
	
	public ActDetial updateActDetial(Integer actNo, Integer memNo, Integer memActStatus){
		ActDetial actDetial = new ActDetial();
		actDetial.setActNo(actNo);
		actDetial.setMemNo(memNo);
		actDetial.setMemActStatus(memActStatus);
		actDetialDao.update(actDetial);
		return actDetial;
	}
	
	public void deleteActDetial(Integer actNo, Integer memNo){
		actDetialDao.delete(actNo, memNo);
	}
	
	public ActDetial getOneActDetial(Integer actNo, Integer memNo){
		return actDetialDao.findByPK(actNo, memNo);
	}
	
	public List<ActDetial> getAll(){
		return actDetialDao.getAll();
	}
}



















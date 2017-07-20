package com.actImg.model;

import java.util.List;

import com.activity.model.ActivityDAO;

public class ActImgService {
	private ActImgDAO_Interface actImgDao;
	
	public ActImgService (){
		actImgDao = new ActImgDAO();
	}
	
	public ActImg addActImg(Integer actNo, String actImgName, String actImgIntro, byte[] actImg){
		ActImg actImgVO = new ActImg();
		actImgVO.setActNo(actNo);
		actImgVO.setActImgName(actImgName);
		actImgVO.setActImgIntro(actImgIntro);
		actImgVO.setActImg(actImg);
		actImgDao.add(actImgVO);
		return actImgVO;
	}
	public ActImg updateActImg(Integer actImgNo,Integer actNo, String actImgName, String actImgIntro, byte[] actImg){
		ActImg actImgVO = new ActImg();
		actImgVO.setActImgNo(actImgNo);
		actImgVO.setActNo(actNo);
		actImgVO.setActImgName(actImgName);
		actImgVO.setActImgIntro(actImgIntro);
		actImgVO.setActImg(actImg);
		actImgDao.update(actImgVO);
		return actImgVO;
	}
	
	public void deleteActImg(Integer actImgNo){
		actImgDao.delete(actImgNo);
	}
	
	public ActImg getOneActImg(Integer actImgNo){
		return actImgDao.findByPK(actImgNo);
	}
	
	public List<ActImg> getAll(){
		return actImgDao.getAll();
	}
	
}

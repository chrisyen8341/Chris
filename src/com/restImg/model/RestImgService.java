package com.restImg.model;

import java.util.List;

public class RestImgService {
	private RestImgDAO_interface restImgDao;
	
	public RestImgService (){
		restImgDao = new RestImgDAO();
	}
	
	public RestImg addRestImg(String restMemId, String restImgName, String restImgIntro, byte[] restImg){
		RestImg restImgVO = new RestImg();
		restImgVO.setRestMemId(restMemId);
		restImgVO.setRestImgName(restImgName);
		restImgVO.setRestImgIntro(restImgIntro);
		restImgVO.setRestImg(restImg);
		restImgDao.add(restImgVO);
		return restImgVO;
	}
	
	public RestImg updateRestImg(Integer restImgNo,String restMemId, String restImgName, String restImgIntro, byte[] restImg){
		RestImg restImgVO = new RestImg();
		restImgVO.setRestImgNo(restImgNo);
		restImgVO.setRestMemId(restMemId);
		restImgVO.setRestImgName(restImgName);
		restImgVO.setRestImgIntro(restImgIntro);
		restImgVO.setRestImg(restImg);
		restImgDao.update(restImgVO);
		return restImgVO;
	}
	
	public void deleteRestImg(Integer restImgNo){
		restImgDao.delete(restImgNo);
	}
	
	public RestImg getOneRestImg(Integer restImgNo){
		return restImgDao.findByPK(restImgNo);
	}
	
	public List<RestImg> getAll(){
		return restImgDao.getAll();
	}
	
	
	
	
}

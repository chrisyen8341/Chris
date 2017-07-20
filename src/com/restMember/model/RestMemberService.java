package com.restMember.model;

import java.util.List;

public class RestMemberService {
	private RestMemberDAO_Interface restMemDao;
	
	public RestMemberService(){
		restMemDao = new RestMemberDAO();
	}
	
	public RestMember addRestMember(String restMemId, Integer restNo, String restMemPsw){
		RestMember restMember = new RestMember();
		restMember.setRestMemId(restMemId);
		restMember.setRestNo(restNo);
		restMember.setRestMemPsw(restMemPsw);
		restMemDao.add(restMember);
		return restMember;
	}
	
	public RestMember updateRestMember(String restMemId, Integer restNo, String restMemPsw){
		RestMember restMember = new RestMember();
		restMember.setRestMemId(restMemId);
		restMember.setRestNo(restNo);
		restMember.setRestMemPsw(restMemPsw);
		restMemDao.update(restMember);
		return restMember;
	}
	
	public void deleteRestMember(String restMemId){
		restMemDao.delete(restMemId);
	}
	
	public RestMember getOneRestMember(String restMemId){
		return restMemDao.findByPK(restMemId);
	}
	
	public List<RestMember> getAll(){
		return restMemDao.getAll();
	}
}





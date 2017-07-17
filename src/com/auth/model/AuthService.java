package com.auth.model;

import java.util.List;


public class AuthService {
	private AuthDAO_interface dao;
	
	public AuthService(){
		dao=new AuthDAO();
	}
	
	public Auth addAuth(String authName) {

		Auth auth = new Auth();
		auth.setAuthName(authName);
		dao.add(auth);

		return auth ;
	}

	public Auth updateAuth(Integer authNo, String authName) {

		Auth auth = new Auth();
		auth.setAuthNo(authNo);
		auth.setAuthName(authName);
		dao.update(auth);

		return auth;
	}

	public void deleteAuth(Integer authNo) {
		dao.delete(authNo);
	}

	public Auth getOneAuth(Integer authNo) {
		return dao.findByPk(authNo);
	}

	public List<Auth> getAll() {
		return dao.getAll();
	}
}

package com.empauth.model;

import java.util.List;

import com.auth.model.Auth;
import com.auth.model.AuthDAO;
import com.auth.model.AuthDAO_interface;

public class EmpAuthService {
private EmpAuthDAO_interface dao;
	
	public EmpAuthService(){
		dao=new EmpAuthDAO();
	}
	
	public EmpAuth addEmpAuth(Integer empNo, Integer authNo) {

		EmpAuth empAuth = new EmpAuth();
		empAuth.setEmpNO(empNo);
		empAuth.setAuthNo(authNo);
		dao.add(empAuth);

		return empAuth ;
	}

	public EmpAuth updateEmpAuth(Integer empNo, Integer authNo) {

		EmpAuth empAuth = new EmpAuth();
		empAuth.setEmpNO(empNo);
		empAuth.setAuthNo(authNo);
		dao.update(empAuth);

		return empAuth;
	}

	public void deleteEmpAuth(Integer empNo, Integer authNo) {
		dao.delete(empNo,authNo);
	}

	public EmpAuth getOneEmpAuth(Integer empNo, Integer authNo) {
		return dao.findByPk(empNo,authNo);
	}

	public List<EmpAuth> getAll() {
		return dao.getAll();
	}
	
	public List<EmpAuth> findByEmpNo(Integer empNo) {
		return dao.findByEmpNo(empNo);
	}
	
}

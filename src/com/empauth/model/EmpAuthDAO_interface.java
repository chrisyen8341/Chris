package com.empauth.model;

import java.sql.Connection;
import java.util.List;


public interface EmpAuthDAO_interface {
	void add(EmpAuth empAuth);
	void add2(EmpAuth empAuth,Connection con);
	void update(EmpAuth empAuth);
	void delete(int empNo,int authNo);
	void delete2(int empNo);
	EmpAuth findByPk(int empNo,int authNo);
	List<EmpAuth> getAll();
	List<EmpAuth> findByEmpNo(int empNo);
}

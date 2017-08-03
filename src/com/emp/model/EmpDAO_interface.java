package com.emp.model;

import java.util.List;
import java.util.Map;



public interface EmpDAO_interface {
	void add(Emp emp);
	void addWithAuth(Emp emp,List<Integer> authNo);
	void update(Emp emp);
	void delete(int empNo);
	void updateWithAuth(Emp emp,List<Integer> auth);
	Emp findByPk(int empNo);
	Emp findById(String empId);
	List<Emp> getAll();
	List<Emp> getAll(Map<String, String[]> map); 
	List<Integer> getAuthByEmpNo(Integer empNo); 
}

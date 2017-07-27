package com.emp.model;

import java.util.List;



public interface EmpDAO_interface {
	void add(Emp emp);
	void addWithAuth(Emp emp,List<Integer> authNo);
	void update(Emp emp);
	void delete(int empNo);
	Emp findByPk(int empNo);
	Emp findById(String empId);
	List<Emp> getAll();
	
}

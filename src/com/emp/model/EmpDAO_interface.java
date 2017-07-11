package com.emp.model;

import java.util.List;



public interface EmpDAO_interface {
	void add(Emp emp);
	void update(Emp emp);
	void delete(int empNo);
	Emp findByPk(int empNo);
	List<Emp> getAll();
}

package com.userquestion.model;

import java.util.List;

public interface UserQuestionDAO_Interface {
	
	public void insert(UserQuestion uq);
	public void update(UserQuestion uq);
	public void delete(int userQNo);
	public UserQuestion findByPrimaryKey(int userQNo);
	public List<UserQuestion> getAll();	
}
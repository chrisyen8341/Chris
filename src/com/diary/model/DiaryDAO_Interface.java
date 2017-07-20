package com.diary.model;

import java.util.List;

public interface DiaryDAO_Interface {
		
		public void insert(Diary diary);
		public void update(Diary diary);
		public void delete(Integer diano);
		public Diary findByPrimaryKey(Integer diano);
		public List<Diary> getAll();
		

}

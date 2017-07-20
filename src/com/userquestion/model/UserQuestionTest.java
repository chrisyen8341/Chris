package com.userquestion.model;

import java.util.List;

public class UserQuestionTest {

	public static void main(String[] args) {
		UserQuestionJDBCDAO dao = new UserQuestionJDBCDAO();

		// // 新增
		// UserQuestion uq1 = new UserQuestion();
		// uq1.setUserQNo(1);
		// uq1.setFaqNo(1);
		// uq1.setUserQCategory("登入問題");
		// uq1.setUserQTitle("我沒辦法登入阿阿阿");
		// uq1.setUserQContent("網站超爛沒辦法登入啦");
		// dao.insert(uq1);
		// System.out.println("新增完惹");

		// // 修改
		// UserQuestion uq2 = new UserQuestion();
		// uq2.setFaqNo(2);
		// uq2.setUserQCategory("登入沒問題");
		// uq2.setUserQTitle("明明就沒問題");
		// uq2.setUserQContent("就你問題最多");
		// uq2.setUserQNo(5);
		// dao.update(uq2);
		// System.out.println("修改完惹");

		// //刪除
		// dao.delete(3);
		// System.out.println("刪掉惹,糟糕");

		// //查詢
		// UserQuestion uq3 = dao.findByPrimaryKey(1);
		// System.out.println(uq3.getUserQNo());
		// System.out.println(uq3.getFaqNo());
		// System.out.println(uq3.getUserQCategory());
		// System.out.println(uq3.getUserQTitle());
		// System.out.println(uq3.getUserQContent());
		// System.out.println("找完惹");

		// 查全部
		List<UserQuestion> uqList = dao.getAll();
		for (UserQuestion uq : uqList) {
			System.out.println("===================");
			System.out.println(uq.getUserQNo());
			System.out.println(uq.getFaqNo());
			System.out.println(uq.getUserQCategory());
			System.out.println(uq.getUserQTitle());
			System.out.println(uq.getUserQContent());
		}

	}

}
package com.news.model;

import java.util.List;

public class NewsTest {

	public static void main(String[] args) {
		NewsJDBCDAO dao = new NewsJDBCDAO();

		// 新增
//		News news1 = new News();
//		news1.setEmpNo(1);
//		news1.setNewsTitle("這是公告");
//		news1.setNewsContent("內容在這邊啦");
//		news1.setNewsDate(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
//		dao.insert(news1);
//		System.out.println("新增完惹");

		// 修改
//		News news2 = new News();
//		news2.setNewsTitle("公告標題改了");
//		news2.setNewsContent("公告內容改了");
//		news2.setNewsDate(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
//		news2.setNewsNo(1);
//		dao.update(news2);
//		System.out.println("修改完惹");

		// 刪除
//		dao.delete(3);
//		System.out.println("刪掉惹,糟糕");

		// 查詢
//		News news3 = dao.findByPrimaryKey(1);
//		System.out.println(news3.getNewsNo());
//		System.out.println(news3.getEmpNo());
//		System.out.println(news3.getNewsTitle());
//		System.out.println(news3.getNewsContent());
//		System.out.println(news3.getNewsDate());
//		System.out.println("找完惹");

		// 查全部
		List<News> newsList = dao.getAll();
		for (News news : newsList) {
			System.out.println(news.getNewsNo());
			System.out.println(news.getEmpNo());
			System.out.println(news.getNewsTitle());
			System.out.println(news.getNewsContent());
			System.out.println(news.getNewsDate());
			System.out.println("===================");
		}

	}

}
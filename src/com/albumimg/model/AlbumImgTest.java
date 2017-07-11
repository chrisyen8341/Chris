package com.albumimg.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AlbumImgTest {

	public static void main(String[] args) {
		AlbumImgJDBCDAO dao=new AlbumImgJDBCDAO();
		
		//新增
//		AlbumImg aImg=new AlbumImg();
//		aImg.setAlbumNo(1);
//		aImg.setImgTitle("相片a");
//		aImg.setImgDesc("測事描宿");
//		aImg.setImgCreatedTime(new Timestamp((new Date()).getTime()));
//		aImg.setImgModifiedTime(new Timestamp((new Date()).getTime()));
//		aImg.setImgFileName("Hello");
//		aImg.setImgExtName("png");
//		aImg.setImgFile(new byte[123465]);
//		dao.add(aImg);
//		System.out.println("=================");
		
		
		//修改
//		AlbumImg aImg2=new AlbumImg();
//		aImg2.setImgNo(2);
//		aImg2.setAlbumNo(1);
//		aImg2.setImgTitle("修改相片");
//		aImg2.setImgDesc("修改相片");
//		aImg2.setImgCreatedTime(new Timestamp((new Date()).getTime()));
//		aImg2.setImgModifiedTime(new Timestamp((new Date()).getTime()));
//		aImg2.setImgFileName("修改");
//		aImg2.setImgExtName("png");
//		aImg2.setImgFile(new byte[123465]);
//		dao.update(aImg2);
//		System.out.println("=================");
		
		//刪除
//		dao.delete(3);
//		System.out.println("=================");
		
		
		//查詢
//		AlbumImg aImg3=dao.findByPk(4);
//		System.out.println(aImg3.getImgNo());
//		System.out.println(aImg3.getImgNo());
//		System.out.println(aImg3.getImgTitle());
//		System.out.println(aImg3.getImgDesc());
//		System.out.println(aImg3.getImgCreatedTime());
//		System.out.println(aImg3.getImgModifiedTime());
//		System.out.println(aImg3.getImgFileName());
//		System.out.println(aImg3.getImgExtName());
//		System.out.println(aImg3.getImgFile());
		
		
		//查全部
//		List<AlbumImg> list=dao.getAll();
//		for(AlbumImg aImg4:list){
//			System.out.println(aImg4.getImgNo());
//			System.out.println(aImg4.getImgNo());
//			System.out.println(aImg4.getImgTitle());
//			System.out.println(aImg4.getImgDesc());
//			System.out.println(aImg4.getImgCreatedTime());
//			System.out.println(aImg4.getImgModifiedTime());
//			System.out.println(aImg4.getImgFileName());
//			System.out.println(aImg4.getImgExtName());
//			System.out.println(aImg4.getImgFile());
//			System.out.println("=================");
//		}

		
	}

}

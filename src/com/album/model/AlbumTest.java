package com.album.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AlbumTest {

	public static void main(String[] args) {
		AlbumJDBCDAO dao=new AlbumJDBCDAO();
		
		
		//新增
		Album album1=new Album();
		album1.setMemNo(1);
		album1.setAlbumTitle("相簿Q");
		album1.setAlbumCreatedTime(new Timestamp((new Date()).getTime()));
		album1.setAlbumModifiedTime(new Timestamp((new Date()).getTime()));
		album1.setAlbumStatus(0);
		album1.setAlbumImgFile(new byte[123]);
		dao.add(album1);
		System.out.println("執行完畢");
		
		
		//修改
//		Album album2=new Album();
//		album2.setAlbumNo(1);
//		album2.setMemNo(1);
//		album2.setAlbumTitle("相簿qqa");
//		album2.setAlbumCreatedTime(new Timestamp((new Date()).getTime()));
//		album2.setAlbumModifiedTime(new Timestamp((new Date()).getTime()));
//		album2.setAlbumStatus(0);
//		album2.setAlbumImgFile(new byte[123]);
//		dao.update(album2);
//		System.out.println("執行完畢");
		
		//刪除
//		dao.delete(2);
//		System.out.println("執行完畢");
		
		
		//查詢
//		Album album4=dao.findByPk(3);
//		System.out.println(album4.getAlbumNo());
//		System.out.println(album4.getMemNo());
//		System.out.println(album4.getAlbumTitle());
//		System.out.println(album4.getAlbumCreatedTime());
//		System.out.println(album4.getAlbumModifiedTime());
//		System.out.println(album4.getAlbumStatus());
//		System.out.println(album4.getAlbumImgFile());
//		System.out.println("執行完畢");
		
		//查全部
//		List<Album> list=dao.getAll();
//		for(Album album:list){
//			System.out.println(album.getAlbumNo());
//			System.out.println(album.getMemNo());
//			System.out.println(album.getAlbumTitle());
//			System.out.println(album.getAlbumCreatedTime());
//			System.out.println(album.getAlbumModifiedTime());
//			System.out.println(album.getAlbumStatus());
//			System.out.println(album.getAlbumImgFile());
//			System.out.println("++++++++++++++++++++++++++++++");
//		}
		
	}

}

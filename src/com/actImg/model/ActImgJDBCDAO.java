package com.actImg.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ActImgJDBCDAO implements ActImgDAO_Interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "petym";
	String passwd = "123456";
	
	private static final String INSERT_ACTIMG = "INSERT INTO ACTIMG(ACTIMGNO,ACTNO,ACTIMGNAME,ACTIMGINTRO,ACTIMG)"
			+ "VALUES(ACTIMG_SEQ.NEXTVAL,?,?,?,?)";
	private static final String UPDATE_ACTIMG = "UPDATE ACTIMG SET ACTNO=?,ACTIMGNAME=?,ACTIMGINTRO=?,ACTIMG=? WHERE ACTIMGNO=?";	
	private static final String DELETE_ACTIMG = "DELETE FROM ACTIMG WHERE ACTIMGNO=?";
	private static final String FIND_BY_PK = "SELECT * FROM ACTIMG WHERE ACTIMGNO = ?";
	private static final String GET_ALL = "SELECT * FROM ACTIMG";
	
	
	@Override
	public void add(ActImg actImg) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(INSERT_ACTIMG);
			pstmt.setInt(1, actImg.getActNo());
			pstmt.setString(2, actImg.getActImgName());
			pstmt.setString(3, actImg.getActImgIntro());
			
			Blob blob = conn.createBlob();	
			blob.setBytes(1, actImg.getActImg());
			pstmt.setBlob(4, blob);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void update(ActImg actImg) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(UPDATE_ACTIMG);
			pstmt.setInt(1, actImg.getActNo());
			pstmt.setString(2, actImg.getActImgName());
			pstmt.setString(3, actImg.getActImgIntro());
			
			Blob blob = conn.createBlob();	
			blob.setBytes(1, actImg.getActImg());
			pstmt.setBlob(4, blob);
			
			pstmt.setInt(5, actImg.getActImgNo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void delete(Integer actImgNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(DELETE_ACTIMG);
			pstmt.setInt(1, actImgNo);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public ActImg findByPK(Integer actImgNo) {
		ActImg actImg = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, actImgNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				actImg = new ActImg();
				actImg.setActImgNo(rs.getInt("ACTIMGNO"));
				actImg.setActNo(rs.getInt("ACTNO"));
				actImg.setActImgName(rs.getString("ACTIMGNAME"));
				actImg.setActImgIntro(rs.getString("ACTIMGINTRO"));
				actImg.setActImg(rs.getBytes("ACTIMG"));
			}
		} catch (Exception e){
			e.getMessage();
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return actImg;
	}
	@Override
	public List<ActImg> getAll() {
		ActImg actImg = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActImg> actImgList = new ArrayList<ActImg>();
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				actImg = new ActImg();
				actImg.setActImgNo(rs.getInt("ACTIMGNO"));
				actImg.setActNo(rs.getInt("ACTNO"));
				actImg.setActImgName(rs.getString("ACTIMGNAME"));
				actImg.setActImgIntro(rs.getString("ACTIMGINTRO"));
				actImg.setActImg(rs.getBytes("ACTIMG"));
				actImgList.add(actImg);
			}
		} catch (Exception e){
			e.getMessage();
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return actImgList;
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
	
		return baos.toByteArray();
	}


	public static void main(String[] args) throws IOException {
		ActImgJDBCDAO actImgJDBCDAO = new ActImgJDBCDAO();
		ActImg actImg = new ActImg();
		
//		actImg.setActNo(2);
//		actImg.setActIngName("相片名稱1");
//		actImg.setActImgIntro("相片介紹1");
//		byte[] actImg1 = getPictureByteArray("C:\\BA102_WebApp\\eclipse_WTP_WorkSpace\\Huang\\WebContent\\img\\2.jpg");
//		actImg.setActImg(actImg1);
//		actImgJDBCDAO.add(actImg);
		
//		actImg.setActNo(2);
//		actImg.setActIngName("相片名稱2");
//		actImg.setActImgIntro("相片介紹2");
//		byte[] actImg1 = getPictureByteArray("C:\\BA102_WebApp\\eclipse_WTP_WorkSpace\\Huang\\WebContent\\img\\1.jpg");
//		actImg.setActImg(actImg1);
//		actImg.setActImgNo(2);
//		actImgJDBCDAO.update(actImg);
		
//		actImgJDBCDAO.delete(3);
		
//		actImg = actImgJDBCDAO.findByPK(1);
//		System.out.println(actImg.getActImgNo());
//		System.out.println(actImg.getActNo());
//		System.out.println(actImg.getActImgName());
//		System.out.println(actImg.getActImgIntro());
		
//		List<ActImg> actImgList = actImgJDBCDAO.getAll();
//		for(ActImg actImgListE :actImgList){
//			System.out.println(actImgListE.getActImgNo());
//			System.out.println(actImgListE.getActNo());
//			System.out.println(actImgListE.getActImgName());
//			System.out.println(actImgListE.getActImgIntro());
//		}
	
	}
	
}



package com.restImg.model;


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



public class RestImgJDBCDAO implements RestImgDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "petym";
	String passwd = "123456";

	private static final String INSERT_RESTIMG = "INSERT INTO RESTIMG(RESTIMGNO,RESTMEMID,RESTIMGNAME,RESTIMGINTRO,RESTIMG)"
			+ "VALUES(RESTIMG_SEQ.NEXTVAL,?,?, ?, ?)";
	private static final String UPDATE_RESTIMG = "UPDATE RESTIMG SET RESTMEMID=?,RESTIMGNAME=?,RESTIMGINTRO=?,RESTIMG=? WHERE RESTIMGNO=?";	
	private static final String DELETE_RESTIMG = "DELETE FROM RESTIMG WHERE RESTIMGNO=?";
	private static final String FIND_BY_PK = "SELECT * FROM RESTIMG WHERE RESTIMGNO = ?";
	private static final String GET_ALL = "SELECT * FROM RESTIMG";

	@Override
	public void add(RestImg restImg) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(INSERT_RESTIMG);
			pstmt.setString(1, restImg.getRestMemId());
			pstmt.setString(2, restImg.getRestImgName());
			pstmt.setString(3, restImg.getRestImgIntro());
			
			Blob blob = conn.createBlob();	
			blob.setBytes(1, restImg.getRestImg());
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
	public void update(RestImg restImg) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {	
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(UPDATE_RESTIMG);
			pstmt.setString(1, restImg.getRestMemId());
			pstmt.setString(2, restImg.getRestImgName());
			pstmt.setString(3, restImg.getRestImgIntro());
			
			Blob blob = conn.createBlob();
			blob.setBytes(1, restImg.getRestImg());
			pstmt.setBlob(4, blob);
			
			pstmt.setInt(5, restImg.getRestImgNo());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			
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
	public void delete(Integer restImgNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {	
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE_RESTIMG);
			pstmt.setInt(1, restImgNo);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.getMessage();
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
	public RestImg findByPK(Integer restImgNo) {
		RestImg restImg = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, restImgNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				restImg = new RestImg();
				restImg.setRestImgNo(rs.getInt("RESTIMGNO"));
				restImg.setRestMemId(rs.getString("RESTMEMID"));
				restImg.setRestImgName(rs.getString("RESTIMGNAME"));
				restImg.setRestImgIntro(rs.getString("RESTIMGINTRO"));
				restImg.setRestImg(rs.getBytes("RESTIMG"));
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
		return restImg;
	}

	@Override
	public List<RestImg> getAll() {
		List<RestImg> restImgList = new ArrayList<RestImg>();
		RestImg restImg = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				restImg = new RestImg();
				restImg.setRestImgNo(rs.getInt("RESTIMGNO"));
				restImg.setRestMemId(rs.getString("RESTMEMID"));
				restImg.setRestImgName(rs.getString("RESTIMGNAME"));
				restImg.setRestImgIntro(rs.getString("RESTIMGINTRO"));
				restImg.setRestImg(rs.getBytes("RESTIMG"));
				restImgList.add(restImg);
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
		
		return restImgList;
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
		RestImgJDBCDAO restImgJDBCDAO = new RestImgJDBCDAO();
		
		
		
		
		RestImg restImg = new RestImg();
//		restImg.setRestMemId("餐會帳號2");
//		restImg.setRestImgName("相片名稱1");
//		restImg.setRestImgIntro("寵餐相片介紹1");
//		byte[] restImg1 = getPictureByteArray("C:\\BA102_WebApp\\eclipse_WTP_WorkSpace\\Huang\\WebContent\\img\\1.jpg");
//		restImg.setRestImg(restImg1);
//		restImgJDBCDAO.add(restImg);
		
//		restImg.setRestMemId("餐會帳號2");
//		restImg.setRestImgName("相片名稱2");
//		restImg.setRestImgIntro("寵餐相片介紹2");
//		byte[] restImg1 = getPictureByteArray("C:\\BA102_WebApp\\eclipse_WTP_WorkSpace\\Huang\\WebContent\\img\\2.jpg");
//		restImg.setRestImg(restImg1);
//		restImg.setRestImgNo(2);
//		restImgJDBCDAO.update(restImg);
		
//		restImgJDBCDAO.delete(3);
		
//		restImg = restImgJDBCDAO.findByPK(2);
//		System.out.println(restImg.getRestImgNo());
//		System.out.println(restImg.getRestMemId());
//		System.out.println(restImg.getRestImgName());
//		System.out.println(restImg.getRestImgIntro());
		
		
		
//		List<RestImg> restImgList = restImgJDBCDAO.getAll();
//		for(RestImg restImgListE : restImgList){
//			System.out.println(restImgListE.getRestImgNo());
//			System.out.println(restImgListE.getRestMemId());
//			System.out.println(restImgListE.getRestImgName());
//			System.out.println(restImgListE.getRestImgIntro());
//		}
	}


}

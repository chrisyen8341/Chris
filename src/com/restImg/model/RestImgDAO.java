package com.restImg.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.sql.DataSource;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class RestImgDAO implements RestImgDAO_interface{
	
	private static DataSource ds = null;
	
	static{
		try{
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_RESTIMG = "INSERT INTO RESTIMG(RESTIMGNO,RESTMEMID,RESTIMGNAME,RESTIMGINTRO,RESTIMG)"
			+ "VALUES(RESTIMG_SEQ.NEXTVAL,?, ?, ? ,?)";
	private static final String UPDATE_RESTIMG = "UPDATE RESTIMG SET RESTMEMID=?,RESTIMGNAME=?,RESTIMGINTRO=?,RESTIMG=? WHERE RESTIMGNO=?";	
	private static final String DELETE_RESTIMG = "DELETE FROM RESTIMG WHERE RESTIMGNO=?";
	private static final String FIND_BY_PK = "SELECT * FROM RESTIMG WHERE RESTIMGNO = ?";
	private static final String GET_ALL = "SELECT * FROM RESTIMG";
	
	@Override
	public void add(RestImg restImg) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			
			String[] cols = {"RESTIMGNO"};
			pstmt = conn.prepareStatement(INSERT_RESTIMG,cols);
			pstmt.setString(1, restImg.getRestMemId());
			pstmt.setString(2, restImg.getRestImgName());
			pstmt.setString(3, restImg.getRestImgIntro());
			pstmt.setBytes(4, restImg.getRestImg());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
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
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(UPDATE_RESTIMG);
			pstmt.setString(1, restImg.getRestMemId());
			pstmt.setString(2, restImg.getRestImgName());
			pstmt.setString(3, restImg.getRestImgIntro());
			pstmt.setBytes(4, restImg.getRestImg());
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
			conn = ds.getConnection();
			
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
			conn = ds.getConnection();
			
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
			conn = ds.getConnection();
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

}







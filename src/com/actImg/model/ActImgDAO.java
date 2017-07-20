package com.actImg.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActImgDAO implements ActImgDAO_Interface{
	private static DataSource ds = null;
	
	static{
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
			conn = ds.getConnection();
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
			conn = ds.getConnection();
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
			conn = ds.getConnection();
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
			conn = ds.getConnection();
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
			conn = ds.getConnection();
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
}

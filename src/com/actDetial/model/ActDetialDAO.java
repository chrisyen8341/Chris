package com.actDetial.model;

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

public class ActDetialDAO implements ActDetialDAO_Interface{

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
	
	private static final String INSERT_ACTDETIAL = "INSERT INTO ACTDETIAL (ACTNO,MEMNO,MEMACTSTATUS) VALUES (?,?,?)";
	private static final String UPDATE_ACTDETIAL = "UPDATE ACTDETIAL SET MEMACTSTATUS=? WHERE ACTNO=? AND MEMNO=?";	
	private static final String DELETE_ACTDETIAL = "DELETE FROM ACTDETIAL WHERE ACTNO=? AND MEMNO=?";
	private static final String FIND_BY_PK = "SELECT * FROM ACTDETIAL WHERE ACTNO=? AND MEMNO=?";
	private static final String GET_ALL = "SELECT * FROM ACTDETIAL";
	
	@Override
	public void add(ActDetial actDetial) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_ACTDETIAL);
			pstmt.setInt(1, actDetial.getActNo());
			pstmt.setInt(2, actDetial.getMemNo());
			pstmt.setInt(3, actDetial.getMemActStatus());
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
	public void update(ActDetial actDetial) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_ACTDETIAL);
			pstmt.setInt(1, actDetial.getMemActStatus());
			pstmt.setInt(2, actDetial.getActNo());
			pstmt.setInt(3, actDetial.getMemNo());
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
	public void delete(Integer actNo, Integer memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_ACTDETIAL);
			pstmt.setInt(1, actNo);
			pstmt.setInt(2, memNo);
			
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
	public ActDetial findByPK(Integer actNo, Integer memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActDetial actDetial = null;
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, actNo);
			pstmt.setInt(2, memNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				actDetial = new ActDetial();
				actDetial.setActNo(rs.getInt("ACTNO"));
				actDetial.setMemNo(rs.getInt("MEMNO"));
				actDetial.setMemActStatus(rs.getInt("MEMACTSTATUS"));
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
		return actDetial;
	}
	
	@Override
	public List<ActDetial> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActDetial actDetial = null;
		List<ActDetial> actDetialList = new ArrayList<ActDetial>();
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				actDetial = new ActDetial();
				actDetial.setActNo(rs.getInt("ACTNO"));
				actDetial.setMemNo(rs.getInt("MEMNO"));
				actDetial.setMemActStatus(rs.getInt("MEMACTSTATUS"));
				actDetialList.add(actDetial);
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
		return actDetialList;
	}

}

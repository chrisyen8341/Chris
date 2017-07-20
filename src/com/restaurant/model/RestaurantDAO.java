package com.restaurant.model;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class RestaurantDAO implements RestaurantDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/petym");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static final String INSERT_REST = "INSERT INTO REST (RESTNO,RESTNAME,RESTADD,RESTPHONE,"
												+ "RESTINTRO,RESTKIND,RESTREVIEWSTATUS,RESTLONGTITUDE,RESTLATITUDE)" + "VALUES(REST_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_REST = "UPDATE REST SET RESTNAME=?,RESTADD=?,RESTPHONE=?"
												+ ",RESTINTRO=?,RESTKIND=?,RESTREVIEWSTATUS=?,RESTLONGTITUDE=?,RESTLATITUDE=? WHERE RESTNO=?";
	private static final String DELETE_REST = "DELETE FROM REST WHERE RESTNO=?";
	private static final String FIND_BY_PK = "SELECT * FROM REST WHERE RESTNO=?";
	private static final String GET_ALL = "SELECT * FROM REST";
	@Override
	public void add(Restaurant rest) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			String[] cols = {"RESTNO"};
			
			pstmt = conn.prepareStatement(INSERT_REST,cols);
			pstmt.setString(1, rest.getRestName());
			pstmt.setString(2, rest.getRestAdd());
			pstmt.setString(3, rest.getRestPhone());
			pstmt.setString(4, rest.getRestIntro());
			pstmt.setInt(5, rest.getRestKind());
			pstmt.setInt(6, rest.getRestReviewStatus());
			pstmt.setDouble(7, rest.getRestLongtitude());
			pstmt.setDouble(8, rest.getRestLatitude());
			
			
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
	public void update(Restaurant rest) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_REST);
			
			pstmt.setString(1, rest.getRestName());
			pstmt.setString(2, rest.getRestAdd());
			pstmt.setString(3, rest.getRestPhone());
			pstmt.setString(4, rest.getRestIntro());
			pstmt.setInt(5, rest.getRestKind());
			pstmt.setInt(6, rest.getRestReviewStatus());
			pstmt.setDouble(7, rest.getRestLongtitude());
			pstmt.setDouble(8, rest.getRestLatitude());
			
			pstmt.setInt(9, rest.getRestNo());
			
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
	public void delete(Integer restNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_REST);
			pstmt.setInt(1, restNo);
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
	public Restaurant findByPK(Integer restNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Restaurant rest = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, restNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				rest = new Restaurant();
				rest.setRestNo(rs.getInt("RESTNO"));
				rest.setRestName(rs.getString("RESTNAME"));
				rest.setRestAdd(rs.getString("RESTADD"));
				rest.setRestPhone(rs.getString("RESTPHONE"));
				rest.setRestIntro(rs.getString("RESTINTRO"));
				rest.setRestKind(rs.getInt("RESTKIND"));
				rest.setRestReviewStatus(rs.getInt("RESTREVIEWSTATUS"));
				rest.setRestLongtitude(rs.getDouble("RESTLONGTITUDE"));
				rest.setRestLatitude(rs.getDouble("RESTLATITUDE"));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return rest;
	}

	@Override
	public List<Restaurant> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Restaurant rest = null;
		List<Restaurant> restList = new ArrayList<Restaurant>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				rest = new Restaurant();
				rest.setRestNo(rs.getInt("restNO"));
				rest.setRestName(rs.getString("restNAME"));
				rest.setRestAdd(rs.getString("restADD"));
				rest.setRestPhone(rs.getString("RestPHONE"));
				rest.setRestIntro(rs.getString("restINTRO"));
				rest.setRestKind(rs.getInt("restKIND"));
				rest.setRestReviewStatus(rs.getInt("RESTREVIEWSTATUS"));
				rest.setRestLongtitude(rs.getDouble("RESTLONGTITUDE"));
				rest.setRestLatitude(rs.getDouble("RESTLATITUDE"));
				restList.add(rest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return restList;
	}
	
	

}







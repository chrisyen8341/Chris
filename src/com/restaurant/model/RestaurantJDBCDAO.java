package com.restaurant.model;

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

import javax.swing.ListModel;

import com.restImg.model.RestImg;

public class RestaurantJDBCDAO implements RestaurantDAO_Interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "petym";
	String passwd = "123456";

	private static final String INSERT_REST = "INSERT INTO REST (RESTNO,RESTNAME,RESTADD,RESTPHONE,"
			+ "RESTINTRO,RESTKIND,RESTREVIEWSTATUS,RESTLONGTITUDE,RESTLATITUDE)" + "VALUES(?,?,?,?,?,?,?,?,?)";
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			

			pstmt = conn.prepareStatement(INSERT_REST);
			pstmt.setInt(1, rest.getRestNo());
			pstmt.setString(2, rest.getRestName());
			pstmt.setString(3, rest.getRestAdd());
			pstmt.setString(4, rest.getRestPhone());
			pstmt.setString(5, rest.getRestIntro());
			pstmt.setInt(6, rest.getRestKind());
			pstmt.setInt(7, rest.getRestReviewStatus());
			pstmt.setDouble(8, rest.getRestLongtitude());
			pstmt.setDouble(9, rest.getRestLatitude());

			

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE_REST);
			pstmt.setInt(1, restNo);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL);
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
				restList.add(rest);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
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
		RestaurantJDBCDAO restJDBCDAO = new RestaurantJDBCDAO();
		
		Restaurant rest = new Restaurant();
//		rest.setRestNo(2);
//		rest.setRestName("À\ÆU¦WºÙ1");
//		rest.setRestAdd("À\ÆU¦a§}1");
//		rest.setRestPhone("À\ÆU¹q¸Ü1");
//		rest.setRestIntro("À\ÆU¤¶²Ð1");
//		rest.setRestKind(1);	
//		rest.setRestReviewStatus(0);
//		rest.setRestLongtitude( 1.111111);
//		rest.setRestLatitude( 2.22222);
//		restJDBCDAO.add(rest);
		
		
//		rest.setRestNo(1);
//		rest.setRestName("À\ÆU¦WºÙ1up");
//		rest.setRestAdd("À\ÆU¦a§}1up");
//		rest.setRestPhone("À\ÆU¹q¸Ü1up");
//		rest.setRestIntro("À\ÆU¤¶²Ð1up");
//		rest.setRestKind(1);	
//		rest.setRestReviewStatus(0);
//		rest.setRestLongtitude( 1.11111);
//		rest.setRestLatitude( 2.222222);
//		restJDBCDAO.update(rest);
		
//		restJDBCDAO.delete(1);
		
//		rest = restJDBCDAO.findByPK(1);
//		System.out.println(rest.getRestNo());
//		System.out.println(rest.getRestName());
//		System.out.println(rest.getRestAdd());
//		System.out.println(rest.getRestPhone());
//		System.out.println(rest.getRestIntro());
//		System.out.println(rest.getRestKind());
		
//		List<Restaurant> restList = restJDBCDAO.getAll();
//		for(Restaurant restE : restList){
//			System.out.println(restE.getRestNo());
//			System.out.println(restE.getRestName());
//			System.out.println(restE.getRestAdd());
//			System.out.println(restE.getRestPhone());
//			System.out.println(restE.getRestIntro());
//			System.out.println(restE.getRestKind());
//		}
	}

}

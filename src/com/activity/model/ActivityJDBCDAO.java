package com.activity.model;

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

import oracle.net.aso.a;

public class ActivityJDBCDAO implements ActivityDAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "petym";
	String passwd = "123456";
	
	private static final String INSERT_ACTIVITY = "INSERT INTO ACTIVITY (ACTNO,RESTMEMID,ACTNAME,ACTCONTENT,ACTDATE,ACTFDATE,"
			+ "ACTSTATUS,ACTULIMIT,ACTLLIMIT,ACTKIND,ACTANOTHERKIND,ACTINITIMG)"
			+ "VALUES(ACTIVITY_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_ACTIVITY = "UPDATE ACTIVITY SET RESTMEMID=?,ACTNAME=?,ACTCONTENT=?,ACTDATE=?"
			+ ",ACTFDATE=?,ACTSTATUS=?,ACTULIMIT=?,ACTLLIMIT=?,ACTKIND=?,ACTANOTHERKIND=?,ACTINITIMG=? WHERE ACTNO=?";
	private static final String DELETE_ACTIVITY = "DELETE FROM ACTIVITY WHERE ACTNO=?";
	private static final String FIND_BY_PK = "SELECT * FROM ACTIVITY WHERE ACTNO=?";
	private static final String GET_ALL = "SELECT * FROM ACTIVITY";
	
	@Override
	public void add(Activity activity) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(INSERT_ACTIVITY);
			
			pstmt.setString(1, activity.getRestMemId());
			pstmt.setString(2, activity.getActName());
			pstmt.setString(3,activity.getActContent());
			pstmt.setDate(4, activity.getActDate());
			pstmt.setDate(5, activity.getActFDate());
			pstmt.setInt(6, activity.getActStatus());
			pstmt.setInt(7, activity.getActULimit());
			pstmt.setInt(8, activity.getActLLimit());
			pstmt.setInt(9, activity.getActKind());
			pstmt.setString(10, activity.getActAnotherKind());
			
			Blob blob = conn.createBlob();
			blob.setBytes(1, activity.getActInitImg());
			pstmt.setBlob(11, blob);
			
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
	public void update(Activity activity) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(UPDATE_ACTIVITY);
			pstmt.setString(1, activity.getRestMemId());
			pstmt.setString(2, activity.getActName());
			pstmt.setString(3, activity.getActContent());
			pstmt.setDate(4, activity.getActDate());
			pstmt.setDate(5, activity.getActFDate());
			pstmt.setInt(6, activity.getActStatus());
			pstmt.setInt(7, activity.getActULimit());
			pstmt.setInt(8, activity.getActLLimit());
			pstmt.setInt(9, activity.getActKind());
			pstmt.setString(10, activity.getActAnotherKind());
			
			Blob blob = conn.createBlob();
			blob.setBytes(1, activity.getActInitImg());
			pstmt.setBlob(11, blob);
			
			
			pstmt.setInt(12, activity.getActNo());
			
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
	public void delete(Integer actNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(DELETE_ACTIVITY);
			pstmt.setInt(1, actNo);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
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
	public Activity findByPK(Integer actNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Activity activity = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, actNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				activity = new Activity();
				activity.setActNo(rs.getInt("ACTNO"));
				activity.setRestMemId(rs.getString("RESTMEMID"));
				activity.setActName(rs.getString("ACTNAME"));
				activity.setActContent(rs.getString("ACTCONTENT"));
				activity.setActDate(rs.getDate("ACTDATE"));
				activity.setActFDate(rs.getDate("ACTFDATE"));
				activity.setActStatus(rs.getInt("ACTSTATUS"));
				activity.setActULimit(rs.getInt("ACTULIMIT"));
				activity.setActLLimit(rs.getInt("ACTLLIMIT"));
				activity.setActKind(rs.getInt("ACTKIND"));
				activity.setActAnotherKind(rs.getString("ACTANOTHERKIND"));
				activity.setActInitImg(rs.getBytes("ACTINITIMG"));
			}
		} catch (Exception e) {
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
		return activity;
	}


	@Override
	public List<Activity> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Activity activity = null;
		ResultSet rs = null;
		List<Activity> activityList = new ArrayList<Activity>();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				activity = new Activity();
				activity.setActNo(rs.getInt("ACTNO"));
				activity.setRestMemId(rs.getString("RESTMEMID"));
				activity.setActName(rs.getString("ACTNAME"));
				activity.setActContent(rs.getString("ACTCONTENT"));
				activity.setActDate(rs.getDate("ACTDATE"));
				activity.setActFDate(rs.getDate("ACTFDATE"));
				activity.setActStatus(rs.getInt("ACTSTATUS"));
				activity.setActULimit(rs.getInt("ACTULIMIT"));
				activity.setActLLimit(rs.getInt("ACTLLIMIT"));
				activity.setActKind(rs.getInt("ACTKIND"));
				activity.setActAnotherKind(rs.getString("ACTANOTHERKIND"));
				activity.setActInitImg(rs.getBytes("ACTINITIMG"));
				activityList.add(activity);
			}
		} catch (Exception e) {
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
		return activityList;
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
		ActivityJDBCDAO activityJDBCDAO = new ActivityJDBCDAO();
		Activity activity = new Activity();
		
//		activity.setRestMemId("餐會帳號2");
//		activity.setActName("餐廳名稱2");
//		activity.setActContent("餐廳內容2");
//		activity.setActDate(java.sql.Date.valueOf("2017-07-17"));
//		activity.setActFDate(java.sql.Date.valueOf("2017-07-24"));
//		activity.setActStatus(1);
//		activity.setActULimit(new Integer(100));
//		activity.setActLLimit(new Integer(10));
//		activity.setActKind(0);
//		activity.setActAnotherKind("");
//		byte[] actInitImg = getPictureByteArray("C:\\BA102_WebApp\\eclipse_WTP_WorkSpace\\Huang\\WebContent\\img\\1.jpg");
//		activity.setActInitImg(actInitImg);
//		activityJDBCDAO.add(activity);
		
//		activity.setRestMemId("餐會帳號2");
//		activity.setActName("餐廳名稱2");
//		activity.setActContent("餐廳內容2");
//		activity.setActDate(java.sql.Date.valueOf("2017-07-17"));
//		activity.setActFDate(java.sql.Date.valueOf("2017-07-24"));
//		activity.setActStatus(1);
//		activity.setActULimit(new Integer(200));
//		activity.setActLLimit(new Integer(20));
//		activity.setActKind(0);
//		activity.setActAnotherKind("");
//		byte[] actInitImg = getPictureByteArray("C:\\BA102_WebApp\\eclipse_WTP_WorkSpace\\Huang\\WebContent\\img\\2.jpg");
//		activity.setActInitImg(actInitImg);
//		activity.setActNo(2);
//		activityJDBCDAO.update(activity);
		
//		activityJDBCDAO.delete(3);
		
//		activity = activityJDBCDAO.findByPK(8001);	
//		System.out.println(activity.getActNo());
//		System.out.println(activity.getRestMemId());
//		System.out.println(activity.getActContent());
//		System.out.println(activity.getActDate());
//		System.out.println(activity.getActFDate());
//		System.out.println(activity.getActStatus());
//		System.out.println(activity.getActULimit());
//		System.out.println(activity.getActLLimit());
//		System.out.println(activity.getActKind());
//		System.out.println(activity.getActAnotherKind());
		
//		List<Activity> activitityList = activityJDBCDAO.getAll();
//		for(Activity activityListE : activitityList){
//			System.out.println(activityListE.getActNo());
//			System.out.println(activityListE.getRestMemId());
//			System.out.println(activityListE.getActContent());
//			System.out.println(activityListE.getActDate());
//			System.out.println(activityListE.getActFDate());
//			System.out.println(activityListE.getActStatus());
//			System.out.println(activityListE.getActULimit());
//			System.out.println(activityListE.getActLLimit());
//			System.out.println(activityListE.getActKind());
//			System.out.println(activityListE.getActAnotherKind());
//		}
	}


	

}

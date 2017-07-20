package com.diary.model;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DiaryJDBCDAO implements DiaryDAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "petym";
	String passwd = "123456";
	
	private static final String INSERT = 
			"INSERT INTO DIARY(diano, memno, dianame, diatext, diaimg, diacretime, diamodtime, diastate)"+
					"VALUES (DIARY_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	private static final String GETALL = 
			"SELECT DIANO,MEMNO,DIANAME, DIATEXT, DIAIMG, DIACRETIME, DIAMODTIME, DIASTATE FROM DIARY";
//			"SELECT DIANO,MEMNO,DIANAME, DIATEXT, DIAIMG, TO_CHAR(DIACREDATE,'YYYY-MM-DD HH12:MI:SS AM') DIACREDATE, TO_CHAR(DIAMODDATE,'YYYY-MM-DD HH12:MI:SS AM') DIAMODDATE, DIASTATE FROM DIARY";
	private static final String GETONE =
			"SELECT DIANO,MEMNO,DIANAME, DIATEXT, DIAIMG, DIACRETIME, DIAMODTIME, DIASTATE FROM DIARY WHERE DIANO = ? ";
	private static final String UPDATE =
			"UPDATE DIARY SET DIANAME=?,DIATEXT=?,DIAIMG=?,diamodtime=? WHERE DIANO=?";
	private static final String DELETE=
			"DELETE FROM DIARY WHERE DIANO=?";	
	
	
	@Override
	public void insert(Diary diaryVO) {
		
		Connection con = null;
		PreparedStatement pstmt =null;
		Clob clob=null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT);			
			clob =con.createClob();
			clob.setString(1, diaryVO.getDiatext());
			
			pstmt.setInt(1, diaryVO.getMemno());
			pstmt.setString(2, diaryVO.getDianame());
			pstmt.setClob(3, clob);
			
			if(diaryVO.getDiaimg()!=null)
				pstmt.setBytes(4, diaryVO.getDiaimg());
			else
				pstmt.setBytes(4, diaryVO.getDiaimg());
			
			pstmt.setTimestamp(5, diaryVO.getDiacretime());
			pstmt.setTimestamp(6, diaryVO.getDiamodtime());
			pstmt.setInt(7, diaryVO.getDiastate());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
				
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(Diary diaryVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		Clob clob=null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			
			pstmt = con.prepareStatement(UPDATE);	
			clob =con.createClob();
			clob.setString(1, diaryVO.getDiatext());
			
			
			pstmt.setString(1, diaryVO.getDianame());
			pstmt.setClob(2, clob);
			pstmt.setBytes(3, diaryVO.getDiaimg());
			pstmt.setTimestamp(4, diaryVO.getDiamodtime());
			pstmt.setInt(5, diaryVO.getDiano());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
				
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer diano) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, diano);
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Diary findByPrimaryKey(Integer diano) {

		Diary diary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETONE);

			pstmt.setInt(1, diano);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				diary = new Diary();
				diary.setDiano(rs.getInt("diano"));
				diary.setMemno(rs.getInt("memno"));
				diary.setDianame(rs.getString("dianame"));
				diary.setDiatext(rs.getString("diatext"));
				diary.setDiaimg(rs.getBytes("diaimg"));
				diary.setDiacretime(rs.getTimestamp("diacretime"));
				diary.setDiamodtime(rs.getTimestamp("diamodtime"));
				diary.setDiastate(rs.getInt("diastate"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return diary;
	}

	@Override
	public List<Diary> getAll() {
		
		List<Diary> list = new ArrayList<Diary>();
		Diary diary = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				diary= new Diary();
				diary.setDiano(rs.getInt("diano"));
				diary.setMemno(rs.getInt("memno"));
				diary.setDianame(rs.getString("dianame"));
				diary.setDiatext(rs.getString("diatext"));
				diary.setDiaimg(rs.getBytes("diaimg"));
				diary.setDiacretime(rs.getTimestamp("diacretime"));
				diary.setDiamodtime(rs.getTimestamp("diamodtime"));
				diary.setDiastate(rs.getInt("diastate"));
				list.add(diary);
			}
			
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		
		DiaryJDBCDAO dao = new DiaryJDBCDAO();
		
		//addDia
		Diary diaVO1 = new Diary();
		diaVO1.setMemno(1012);
		diaVO1.setDianame("好貓12");
		diaVO1.setDiatext("今天貓兒真乖");
		diaVO1.setDiaimg(null);
		diaVO1.setDiacretime(new java.sql.Timestamp(System.currentTimeMillis()));
		diaVO1.setDiamodtime(new java.sql.Timestamp(System.currentTimeMillis()));
		diaVO1.setDiastate(0);
		
		dao.insert(diaVO1);
		
		//modify
//		Diary diary =new Diary();
//		diary.setDiano(1022);
//		diary.setDianame("貓貓");
//		diary.setDiatext("不用上課");
//		diary.setDiamodtime(new java.sql.Timestamp(System.currentTimeMillis()));
//		diary.setDiaimg(null);
//		
//		dao.update(diary);
		
		//delete
		
//		dao.delete(1022);
		
		//query
//		Diary diary1 = dao.findByPrimaryKey(1028);
//		System.out.print(diary1.getDiano()+" ,");
//		System.out.print(diary1.getMemno()+" ,");
//		System.out.print(diary1.getDianame()+" ,");
//		System.out.print(diary1.getDiatext()+" ,");
//		System.out.print(diary1.getDiaimg()+" ,");
//		System.out.print(diary1.getDiacretime()+" ,");
//		System.out.print(diary1.getDiamodtime()+" ,");
//		System.out.print(diary1.getDiastate()+" ,");
//		System.out.println("---------------------------------");
		
		
		//query all
//		List<Diary> list = dao.getAll();
//		for(Diary dia : list){
//			System.out.print(dia.getDiano()+" ,");
//			System.out.print(dia.getMemno()+" ,");
//			System.out.print(dia.getDianame()+" ,");
//			System.out.print(dia.getDiatext()+" ,");
//			System.out.print(dia.getDiaimg()+" ,");
//			System.out.print(dia.getDiacretime()+" ,");
//			System.out.print(dia.getDiamodtime()+" ,");
//			System.out.print(dia.getDiastate()+" ,");
//			System.out.println("---------------------------------");
//		}
		
		System.out.println("結束");
		
		
		
		
	}

}

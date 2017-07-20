package com.diary.model;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.UnavailableException;
import javax.sql.DataSource;

import oracle.sql.CLOB;

public class DiaryDAO implements DiaryDAO_Interface{

	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		}catch(Exception e){
			try {
				throw new UnavailableException("Couldn't get db connection");
			} catch (UnavailableException e1) {
				e1.printStackTrace();
			}
		}
	}
	
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
	public void insert(Diary diary) {
			
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);			
		
			pstmt.setInt(1, diary.getMemno());
			pstmt.setString(2, diary.getDianame());
			Clob clob =con.createClob();
			clob.setString(1, diary.getDiatext());
			pstmt.setClob(3, clob);
			
			if(diary.getDiaimg()!=null)
				pstmt.setBytes(4, diary.getDiaimg());
			else
				pstmt.setBytes(4, null);
			
			pstmt.setTimestamp(5, diary.getDiacretime());
			pstmt.setTimestamp(6, diary.getDiacretime());
			pstmt.setInt(7, diary.getDiastate());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
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
	public void update(Diary diary) {
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt =con.prepareStatement(UPDATE);
			
			pstmt.setString(1, diary.getDianame());
			pstmt.setString(2, diary.getDiatext());
			pstmt.setBytes(3, diary.getDiaimg());
			pstmt.setTimestamp(4, diary.getDiamodtime());
			pstmt.setInt(5, diary.getDiano());
			
			pstmt.executeUpdate();
			
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer diano) {

		Connection con = null;
		PreparedStatement pstmt= null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, diano);
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
		ResultSet rs =null;
		
		try{
			con =ds.getConnection();
			pstmt =con.prepareStatement(GETONE);
			pstmt.setInt(1, diano);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
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
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(rs!=null){
					try{
						rs.close();
					}catch(SQLException e){
						e.printStackTrace();
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
		Diary diaryVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				diaryVO= new Diary();
				diaryVO.setDiano(rs.getInt("diano"));
				diaryVO.setMemno(rs.getInt("memno"));
				diaryVO.setDianame(rs.getString("dianame"));
				diaryVO.setDiatext(rs.getString("diatext"));
				diaryVO.setDiaimg(rs.getBytes("diaimg"));
				diaryVO.setDiacretime(rs.getTimestamp("diacretime"));
				diaryVO.setDiamodtime(rs.getTimestamp("diamodtime"));
				diaryVO.setDiastate(rs.getInt("diastate"));
				list.add(diaryVO);
			}
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
	
	

}

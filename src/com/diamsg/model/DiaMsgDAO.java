package com.diamsg.model;


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



public class DiaMsgDAO implements DiaMsgDAO_Interface{

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
			"INSERT INTO DIAMSG(DIAMSGNO, DIANO, MEMNO, DIAMSGTEXT, DIAMSGDATE, DIAMSGSTATE) VALUES(DIAMSG_SEQ.NEXTVAL ,?,?,?,?,?)";
	private static final String GETALL = 
			"SELECT DIAMSGNO, DIANO, MEMNO, DIAMSGTEXT, DIAMSGDATE, DIAMSGSTATE FROM DIAMSG ORDER BY DIAMSGDATE";
	private static final String GETONE =
			"SELECT DIAMSGNO, DIANO, MEMNO, DIAMSGTEXT, DIAMSGDATE, DIAMSGSTATE FROM DIAMSG WHERE DIAMSGNO = ? ";
	private static final String UPDATE =
			"UPDATE DIAMSG SET DIAMSGTEXT=?, DIAMSGDATE=? WHERE DIAMSGNO=?";
	private static final String DELETE=
			"DELETE FROM DIAMSG WHERE DIAMSGNO=?";
	
	@Override
	public void insert(DiaMsg diaMsg) {
		
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);			
		
			pstmt.setInt(1, diaMsg.getDiaNO());
			pstmt.setInt(2, diaMsg.getMemNo());
			pstmt.setString(3, diaMsg.getDiaMsgText());
			pstmt.setTimestamp(4, diaMsg.getDiaMsgTime());
			pstmt.setInt(5, diaMsg.getDiaMsgState());
			
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
	public void update(DiaMsg diaMsg) {
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt =con.prepareStatement(UPDATE);
			
			pstmt.setString(1, diaMsg.getDiaMsgText());
			pstmt.setTimestamp(2, diaMsg.getDiaMsgTime());
			pstmt.setInt(3, diaMsg.getDiaMsgNO());
			
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
	public void delete(Integer diaMsgNo) {
		
		Connection con = null;
		PreparedStatement pstmt= null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, diaMsgNo);
			
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
	public DiaMsg findByPrimaryKey(Integer diaMsgNo) {
		
		DiaMsg diaMsg = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try{
			con =ds.getConnection();
			pstmt =con.prepareStatement(GETONE);
			pstmt.setInt(1, diaMsgNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				diaMsg = new DiaMsg();
				diaMsg.setDiaMsgNO(rs.getInt("diamsgno"));
				diaMsg.setDiaNO(rs.getInt("diano"));
				diaMsg.setMemNo(rs.getInt("memno"));
				diaMsg.setDiaMsgText(rs.getString("diamsgtext"));
				diaMsg.setDiaMsgTime(rs.getTimestamp("diamsgdate"));
				diaMsg.setDiaMsgState(rs.getInt("diamsgstate"));
				
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
		return diaMsg;
	}

	@Override
	public List<DiaMsg> getAll() {
		
		List<DiaMsg> list = new ArrayList<DiaMsg>();
		DiaMsg diaMsg = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con =ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {
				
				diaMsg = new DiaMsg();
				diaMsg.setDiaMsgNO(rs.getInt("diamsgno"));
				diaMsg.setDiaNO(rs.getInt("diano"));
				diaMsg.setMemNo(rs.getInt("memno"));
				diaMsg.setDiaMsgText(rs.getString("diamsgtext"));
				diaMsg.setDiaMsgTime(rs.getTimestamp("diamsgdate"));
				diaMsg.setDiaMsgState(rs.getInt("diamsgstate"));
				list.add(diaMsg);
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return list;
	}

}

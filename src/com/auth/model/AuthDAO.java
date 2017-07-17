package com.auth.model;

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

public class AuthDAO implements AuthDAO_interface {

	private static DataSource ds;
	private int currSeq;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = "INSERT INTO AUTH(AUTHNO, AUTHNAME)"
			+ " VALUES(AUTHNO_SQ.NEXTVAL,?)";
	private static final String UPDATE_STMT = "UPDATE AUTH SET AUTHNO = ?, AUTHNAME = ? WHERE AUTHNO =　?";
	private static final String DELETE_AUTH = "DELETE FROM AUTH WHERE AUTHNO = ?";
	private static final String DELETE_EMPAUTH = "DELETE FROM EMPAUTH WHERE AUTHNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM AUTH WHERE AUTHNO = ?";
	private static final String GET_ALL = "SELECT * FROM AUTH";
	
	
	@Override
	public void add(Auth auth) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, auth.getAuthName());
			pstmt.executeUpdate();

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
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
	public void update(Auth auth) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, auth.getAuthNo());
			pstmt.setString(2, auth.getAuthName());
			pstmt.setInt(3, auth.getAuthNo());
			pstmt.executeUpdate();

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
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
	public void delete(int authNo) {
		int updateCount_EmpAuths = 0;
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			//先刪員工權限 EMPAUTH
			pstmt = con.prepareStatement(DELETE_EMPAUTH);
			pstmt.setInt(1, authNo);
			updateCount_EmpAuths=pstmt.executeUpdate();
			
			//再刪權限 EMP
			pstmt = con.prepareStatement(DELETE_AUTH);
			pstmt.setInt(1, authNo);
			pstmt.executeUpdate();
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除權限編號" + authNo + "時,共有" + updateCount_EmpAuths
					+ "個員工權限同時被刪除");
			

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
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
	public Auth findByPk(int authNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Auth auth=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, authNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				auth=new Auth();
				auth.setAuthNo(rs.getInt("authNo"));
				auth.setAuthName(rs.getString("authName"));
			}
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
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
		return auth;
	}

	@Override
	public List<Auth> getAll() {
		List<Auth> authList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Auth auth=new Auth();
				auth.setAuthNo(rs.getInt("authNo"));
				auth.setAuthName(rs.getString("authName"));
				authList.add(auth);		
			}
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
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
		return authList;
	}

}

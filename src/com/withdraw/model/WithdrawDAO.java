package com.withdraw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.charge.model.Charges;

public class WithdrawDAO implements Withdraw_interface{
	private static DataSource ds;


	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO WITHDRAW(WITHDRAWNO,MEMNO,WITHDRAWNUM,APPLYTIME,WITHDRAWSTATUS)"+ "VALUES(WITHDRAWNO_SQ.NEXTVAL,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE WITHDRAW SET WITHDRAWNO = ?, MEMNO = ?, WITHDRAWNUM = ?,APPLYTIME = ?,WITHDRAWSTATUS = ?"
			+ "WHERE WITHDRAWNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM WITHDRAW WHERE WITHDRAWNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM WITHDRAW WHERE WITHDRAWNO = ?";
	private static final String GET_ALL = "SELECT * FROM WITHDRAW";
	@Override
	public void add(Withdraw withdraw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, withdraw.getWithdrawNo());
			pstmt.setInt(2, withdraw.getMemNo());
			pstmt.setInt(3, withdraw.getWithdrawNum());
			pstmt.setDate(4, withdraw.getApplyTime());
			pstmt.setInt(5, withdraw.getWithdrawStatus());
			pstmt.executeUpdate();

			
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Withdraw withdraw) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, withdraw.getWithdrawNo());
			pstmt.setInt(2, withdraw.getMemNo());
			pstmt.setInt(3, withdraw.getWithdrawNum());
			pstmt.setDate(4, withdraw.getApplyTime());
			pstmt.setInt(5, withdraw.getWithdrawStatus());
			pstmt.setInt(6, withdraw.getWithdrawNo());
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
	public void delete(int withdrawNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, withdrawNo);
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
	public Withdraw findByPk(int withdrawNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Withdraw withdraw=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, withdrawNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				withdraw=new Withdraw();
				withdraw.setWithdrawNo(rs.getInt("withdrawNo"));
				withdraw.setMemNo(rs.getInt("memNo"));
				withdraw.setWithdrawNum(rs.getInt("withdrawNum"));
				withdraw.setApplyTime(rs.getDate("applyTime"));
				withdraw.setWithdrawStatus(rs.getInt("withdrawStatus"));
				
			
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
		return withdraw;
	}

	@Override
	public List<Withdraw> getAll() {
		List<Withdraw> withdrawList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Withdraw withdraw=new Withdraw();
				withdraw.setWithdrawNo(rs.getInt("withdrawNo"));
				withdraw.setMemNo(rs.getInt("memNo"));
				withdraw.setWithdrawNum(rs.getInt("withdrawNum"));
				withdraw.setApplyTime(rs.getDate("applyTime"));
				withdraw.setWithdrawStatus(rs.getInt("withdrawStatus"));
				withdrawList.add(withdraw);		
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
		return withdrawList;
	}

}

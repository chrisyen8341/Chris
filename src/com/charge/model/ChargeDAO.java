package com.charge.model;

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

import com.orderlist.model.OrderList;
import com.product.model.Product;


public class ChargeDAO implements Charge_interface{
	private static DataSource ds;


	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO CHARGES(CHARGENO,MEMNO,CHARGENUM,APPLYTIME,CHARGESTATUS,PAYWAY)"+ "VALUES(CHARGENO_SQ.NEXTVAL,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE CHARGES SET CHARGENO = ?, MEMNO = ?, CHARGENUM = ?,APPLYTIME = ?,CHARGESTATUS = ?,PAYWAY = ?"
			+ "WHERE CHARGENO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM CHARGES WHERE CHARGENO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CHARGES WHERE CHARGENO = ?";
	private static final String GET_ALL = "SELECT * FROM CHARGES";
	@Override
	public void add(Charges charges) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, charges.getMemNo());
			pstmt.setInt(2, charges.getChargeNum());
			pstmt.setDate(3, charges.getApplyTime());
			pstmt.setInt(4, charges.getChargeStatus());
			pstmt.setInt(5, charges.getPayWay());
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
	public void update(Charges charges) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, charges.getChargeNo());
			pstmt.setInt(2, charges.getMemNo());
			pstmt.setInt(3, charges.getChargeNum());
			pstmt.setDate(4, charges.getApplyTime());
			pstmt.setInt(5, charges.getChargeStatus());
			pstmt.setInt(6, charges.getPayWay());
			pstmt.setInt(7, charges.getChargeNo());
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
	public void delete(int chargeNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, chargeNo);
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
	public Charges findByPk(int chargeNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Charges charge=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, chargeNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				charge=new Charges();
				charge.setChargeNo(rs.getInt("chargeNo"));
				charge.setMemNo(rs.getInt("memNo"));
				charge.setChargeNum(rs.getInt("chargeNum"));
				charge.setApplyTime(rs.getDate("applyTime"));
				charge.setChargeStatus(rs.getInt("chargeStatus"));
				charge.setPayWay(rs.getInt("payWay"));
			
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
		return charge;
	}

	@Override
	public List<Charges> getAll() {
		List<Charges> chargeList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Charges charge=new Charges();
				charge.setChargeNo(rs.getInt("chargeNo"));
				charge.setMemNo(rs.getInt("memNo"));
				charge.setChargeNum(rs.getInt("chargeNum"));
				charge.setApplyTime(rs.getDate("applyTime"));
				charge.setChargeStatus(rs.getInt("chargeStatus"));
				charge.setPayWay(rs.getInt("payWay"));
				chargeList.add(charge);
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
		return chargeList;
	}

}

package com.order.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdDAO implements Ord_interface{
		
	private static DataSource ds;


	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ORD(ORDNO,MEMNO,ORDDATE,ORDCHECK,ORDPRODUCT,ORDCLOSE,ORDSTATUS,ORDSHIP,ORDTOTAL,CONNAME,CONADD,CONTEL)"
			+ "VALUES(ORDNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ORD SET ORDNO = ?, MEMNO = ?, ORDDATE = ?, "
			+ "ORDCHECK = ?, ORDPRODUCT = ?, ORDCLOSE = ?, ORDSTATUS = ?,ORDSHIP = ? ,ORDTOTAL = ? ,CONNAME = ? ,CONADD = ? ,CONTEL = ? WHERE ORDNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM ORD WHERE ORDNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ORD WHERE ORDNO = ?";
	private static final String GET_ALL = "SELECT * FROM ORD";
	@Override
	public void add(Ord order) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, order.getMemNo());
			pstmt.setDate(2, order.getOrdDate());
			pstmt.setDate(3, order.getOrdCheck());
			pstmt.setDate(4, order.getOrdProduct());
			pstmt.setDate(5, order.getOrdClose());
			pstmt.setInt(6, order.getOrdStatus());
			pstmt.setInt(7, order.getOrdShip());
			pstmt.setInt(8, order.getOrdTotal());
			pstmt.setString(9, order.getConName());
			pstmt.setString(10, order.getConAdd());
			pstmt.setString(11, order.getConTel());
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
	public void delete(int ordNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ordNo);
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
	public void update(Ord order) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, order.getOrdNo());
			pstmt.setInt(2, order.getMemNo());
			pstmt.setDate(3, order.getOrdDate());
			pstmt.setDate(4, order.getOrdCheck());
			pstmt.setDate(5, order.getOrdProduct());
			pstmt.setDate(6, order.getOrdClose());
			pstmt.setInt(7, order.getOrdStatus());
			pstmt.setInt(8, order.getOrdShip());
			pstmt.setInt(9, order.getOrdTotal());
			pstmt.setString(10, order.getConName());
			pstmt.setString(11, order.getConAdd());
			pstmt.setString(12, order.getConTel());
			pstmt.setInt(13, order.getOrdNo());
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
	public Ord findByPk(int ordNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Ord order=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, ordNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				order=new Ord();
				order.setOrdNo(rs.getInt("ordNo"));
				order.setMemNo(rs.getInt("memNo"));
				order.setOrdDate(rs.getDate("ordDate"));
				order.setOrdCheck(rs.getDate("ordCheck"));
				order.setOrdProduct(rs.getDate("ordProduct"));
				order.setOrdClose(rs.getDate("ordClose"));
				order.setOrdStatus(rs.getInt("ordStatus"));
				order.setOrdShip(rs.getInt("ordShip"));
				order.setOrdTotal(rs.getInt("ordTotal"));
				order.setConName(rs.getString("conName"));
				order.setConAdd(rs.getString("conAdd"));
				order.setConTel(rs.getString("conTel"));
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
		return order;
	}
	@Override
	public List<Ord> getAll() {
		List<Ord> orderList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Ord order=new Ord();
				order.setOrdNo(rs.getInt("ordNo"));
				order.setMemNo(rs.getInt("memNo"));
				order.setOrdDate(rs.getDate("ordDate"));
				order.setOrdCheck(rs.getDate("ordCheck"));
				order.setOrdProduct(rs.getDate("ordProduct"));
				order.setOrdClose(rs.getDate("ordClose"));
				order.setOrdStatus(rs.getInt("ordStatus"));
				order.setOrdShip(rs.getInt("ordShip"));
				order.setOrdTotal(rs.getInt("ordTotal"));
				order.setConName(rs.getString("conName"));
				order.setConAdd(rs.getString("conAdd"));
				order.setConTel(rs.getString("conTel"));
				orderList.add(order);		
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
		return orderList;
	}
}

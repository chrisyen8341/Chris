package com.orderlist.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order.model.Ord;

public class OrderListDAO implements OrderList_interface{
	
	private static DataSource ds;


	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ORDERLIST(ORDNO,PRODNO,PROPRICE,PROQUA)"+ "VALUES(ORDNO_SQ.NEXTVAL,PRODNO_SQ.NEXTVAL,?,?)";
	private static final String UPDATE_STMT = "UPDATE ORDERLIST SET ORDNO = ?, PRODNO = ?, PROPRICE = ?, "
			+ "PROQUA = ?WHERE ORDNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM ORDERLIST WHERE ORDNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ORDERLIST WHERE ORDNO = ?";
	private static final String GET_ALL = "SELECT * FROM ORDERLIST";
	@Override
	public void add(OrderList ol) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, ol.getProPrice());
			pstmt.setInt(2, ol.getProQua());
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
	public void update(OrderList ol) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, ol.getOrdNo());
			pstmt.setInt(2, ol.getProdNo());
			pstmt.setInt(3, ol.getProPrice());
			pstmt.setInt(4, ol.getProQua());
			pstmt.setInt(5, ol.getOrdNo());
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
	public void delete(int olNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, olNo);
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
	public OrderList findByPk(int olNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		OrderList ol=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, olNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ol=new OrderList();
				ol.setOrdNo(rs.getInt("ordNo"));
				ol.setProdNo(rs.getInt("prodNo"));
				ol.setProPrice(rs.getInt("proPrice"));
				ol.setProQua(rs.getInt("proQua"));
				
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
		return ol;
		
	}

	@Override
	public List<OrderList> getAll() {
		List<OrderList> orderList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				OrderList orderlist=new OrderList();
				orderlist.setOrdNo(rs.getInt("ordNo"));
				orderlist.setProdNo(rs.getInt("proNo"));
				orderlist.setProPrice(rs.getInt("proPrice"));
				orderlist.setProQua(rs.getInt("proQua"));
				orderList.add(orderlist);		
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

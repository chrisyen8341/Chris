package com.orderlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderListJDBCDAO implements OrderList_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "petym";
	private static final String PASSWORD = "123456";
	
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
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_STMT);
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
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, ol.getOrdNo());
			pstmt.setInt(2, ol.getProdNo());
			pstmt.setInt(3, ol.getProPrice());
			pstmt.setInt(4, ol.getProQua());
			pstmt.setInt(5, ol.getOrdNo());
			pstmt.executeUpdate();

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, olNo);
			pstmt.executeUpdate();

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_PK);
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
		} catch (ClassNotFoundException e) {
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
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				OrderList orderlists=new OrderList();
				orderlists.setOrdNo(rs.getInt("ordNo"));
				orderlists.setProdNo(rs.getInt("prodNo"));
				orderlists.setProPrice(rs.getInt("proPrice"));
				orderlists.setProQua(rs.getInt("proQua"));
				orderList.add(orderlists);		
			}
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

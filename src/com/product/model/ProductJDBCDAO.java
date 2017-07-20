package com.product.model;

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

import com.order.model.Ord_interface;

public class ProductJDBCDAO implements Product_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "petym";
	private static final String PASSWORD = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO PRODUCT(PRODNO,PRODNAME,PRODPRICE,PRODIMG,PRODDESCPT,PRODADD,PRODOUT,PRODSTATE,PRODTYPE)"
			+ " VALUES(PRODNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE PRODUCT SET PRODNO = ?,PRODNAME = ?,"
			+ "PRODPRICE = ?, PRODIMG = ?, PRODDESCPT = ?, PRODADD = ?, PRODOUT = ?,PRODSTATE = ?,PRODTYPE = ?"+ "WHERE PRODNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM PRODUCT WHERE PRODNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PRODUCT WHERE PRODNO = ?";
	private static final String GET_ALL = "SELECT * FROM PRODUCT";
	
	@Override
	public void add(Product prod) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, prod.getProdNo());
			pstmt.setString(2, prod.getProdName());
			pstmt.setInt(3, prod.getProdPrice());
			Blob blob = con.createBlob();
			blob.setBytes(1, prod.getProdImg());
			pstmt.setBlob(4, blob);
			pstmt.setString(5, prod.getProdDescpt());
			pstmt.setDate(6, prod.getProdAdd());
			pstmt.setDate(7, prod.getProdOut());
			pstmt.setInt(8, prod.getProdState());
			pstmt.setString(9, prod.getProdType());
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
	public void update(Product prod) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, prod.getProdNo());
			pstmt.setString(2, prod.getProdName());
			pstmt.setInt(3, prod.getProdPrice());
			Blob blob = con.createBlob();
			blob.setBytes(1, prod.getProdImg());
			pstmt.setBlob(4, blob);
			pstmt.setString(5, prod.getProdDescpt());
			pstmt.setDate(6, prod.getProdAdd());
			pstmt.setDate(7, prod.getProdOut());
			pstmt.setInt(8, prod.getProdState());
			pstmt.setString(9, prod.getProdType());
			pstmt.setInt(10, prod.getProdNo());
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
	public void delete(int prodNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, prodNo);
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
	public Product findByPk(int prodNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Product product=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, prodNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				product=new Product();
				product.setProdNo(rs.getInt("prodNo"));
				product.setProdName(rs.getString("prodName"));
				product.setProdPrice(rs.getInt("prodPrice"));
				product.setProdImg(rs.getBytes("prodImg"));
				product.setProdDescpt(rs.getString("prodDescpt"));
				product.setProdAdd(rs.getDate("prodAdd"));
				product.setProdOut(rs.getDate("prodOut"));
				product.setProdState(rs.getInt("prodState"));
				product.setProdType(rs.getString("prodType"));
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
		return product;
	}

	@Override
	public List<Product> getAll() {
		List<Product> productList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setProdNo(rs.getInt("prodNo"));
				product.setProdName(rs.getString("prodName"));
				product.setProdPrice(rs.getInt("prodPrice"));
				product.setProdImg(rs.getBytes("prodImg"));
				product.setProdDescpt(rs.getString("prodDescpt"));
				product.setProdAdd(rs.getDate("prodAdd"));
				product.setProdOut(rs.getDate("prodOut"));
				product.setProdState(rs.getInt("prodState"));
				product.setProdType(rs.getString("prodType"));
				productList.add(product);		
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
		return productList;
		}
	public final static byte[] getPictureByteArray(String path) throws IOException {
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
}

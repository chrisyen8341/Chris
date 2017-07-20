package com.ad.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdJDBCDAO implements AdDAO_Interface {

	final String driver = "oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "petym";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Ad(adNO, adContent, adImg, adOnline, adOffline, adFee)"
			+ " VALUES(ad_SQ.NEXTVAL,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE Ad SET adContent = ?, adImg = ?, adOnline = ?, adOffline = ?, adFee = ? WHERE adNO = ?";
	private static final String DELETE_STMT = "DELETE FROM AD WHERE adNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Ad WHERE adNO = ?";
	private static final String GET_ALL = "SELECT * FROM Ad";

	@Override
	public void insert(Ad ad) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ad.getAdContent());
			Blob blob = con.createBlob();
			blob.setBytes(2, ad.getAdImg());
			pstmt.setTimestamp(3, ad.getAdOnline());
			pstmt.setTimestamp(4, ad.getAdOffline());
			pstmt.setDouble(5, ad.getAdFee());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Ad ad) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, ad.getAdContent());
			pstmt.setBytes(2, ad.getAdImg());
			pstmt.setTimestamp(3, ad.getAdOnline());
			pstmt.setTimestamp(4, ad.getAdOffline());
			pstmt.setDouble(5, ad.getAdFee());
			pstmt.setInt(6, ad.getAdNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(int adNo) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, adNo);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Ad findByPrimaryKey(int adNo) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		Ad ad = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, adNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ad = new Ad();
				ad.setAdNo(rs.getInt("adNo"));
				ad.setAdContent(rs.getString("adContent"));
				ad.setAdImg(rs.getBytes("adImg"));
				ad.setAdOnline(rs.getTimestamp("adOnline"));
				ad.setAdOffline(rs.getTimestamp("adOffline"));
				ad.setAdFee(rs.getDouble("adFee"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return ad;
	}

	@Override
	public List<Ad> getAll() {
		List<Ad> adList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Ad ad = new Ad();
				ad.setAdNo(rs.getInt("adNo"));
				ad.setAdContent(rs.getString("adContent"));
				ad.setAdImg(rs.getBytes("adImg"));
				ad.setAdOnline(rs.getTimestamp("adOnline"));
				ad.setAdOffline(rs.getTimestamp("adOffline"));
				ad.setAdFee(rs.getDouble("adFee"));
				adList.add(ad);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return adList;
	}

}
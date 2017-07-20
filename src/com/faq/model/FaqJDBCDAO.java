package com.faq.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FaqJDBCDAO implements FaqDAO_Interface {

	final String driver = "oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "petym";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Faq(faqNO, empNo, faqCategory, faqTitle, faqAnswer)"
			+ " VALUES(faq_SQ.NEXTVAL,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE Faq SET empNO = ?, faqCategory = ?, faqTitle = ?, faqAnswer = ? WHERE faqNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Faq WHERE faqNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Faq WHERE faqNO = ?";
	private static final String GET_ALL = "SELECT * FROM Faq";

	@Override
	public void insert(Faq faq) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, faq.getEmpNo());
			pstmt.setString(2, faq.getFaqCategory());
			pstmt.setString(3, faq.getFaqTitle());
			pstmt.setString(4, faq.getFaqAnswer());
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
	public void update(Faq faq) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, faq.getEmpNo());
			pstmt.setString(2, faq.getFaqCategory());
			pstmt.setString(3, faq.getFaqTitle());
			pstmt.setString(4, faq.getFaqAnswer());
			pstmt.setInt(5, faq.getFaqNo());
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
	public void delete(int faqNo) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, faqNo);
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
	public Faq findByPrimaryKey(int faqNo) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		Faq faq = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, faqNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				faq = new Faq();
				faq.setFaqNo(rs.getInt("faqNo"));
				faq.setFaqCategory(rs.getString("faqCategory"));
				faq.setFaqTitle(rs.getString("faqTitle"));
				faq.setFaqAnswer(rs.getString("faqAnswer"));

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
		return faq;
	}

	@Override
	public List<Faq> getAll() {
		List<Faq> faqList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Faq faq = new Faq();
				faq.setFaqNo(rs.getInt("faqNo"));
				faq.setEmpNo(rs.getInt("empNo"));
				faq.setFaqCategory(rs.getString("faqCategory"));
				faq.setFaqTitle(rs.getString("faqTitle"));
				faq.setFaqAnswer(rs.getString("faqAnswer"));
				faqList.add(faq);

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
		return faqList;
	}

}
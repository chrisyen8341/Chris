package com.userquestion.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserQuestionJDBCDAO implements UserQuestionDAO_Interface {

	final String driver = "oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "petym";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO UserQuestion(userQNo, faqNo, userQCategory, userQTitle, userQContent)"
			+ " VALUES(USERQUESTION_SQ.NEXTVAL,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE UserQuestion SET faqNo = ?, userQCategory = ?, userQTitle = ?, userQContent = ? WHERE userQNo =?";
	private static final String DELETE_STMT = "DELETE FROM UserQuestion WHERE userQNo = ?";
	private static final String FIND_BY_PK = "SELECT * FROM UserQuestion WHERE faqNo = ?";
	private static final String GET_ALL = "SELECT * FROM UserQuestion";

	@Override
	public void insert(UserQuestion uq) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, uq.getFaqNo());
			pstmt.setString(2, uq.getUserQCategory());
			pstmt.setString(3, uq.getUserQTitle());
			pstmt.setString(4, uq.getUserQContent());
			pstmt.setInt(5, uq.getUserQNo());
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
	public void update(UserQuestion uq) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, uq.getFaqNo());
			pstmt.setString(2, uq.getUserQCategory());
			pstmt.setString(3, uq.getUserQTitle());
			pstmt.setString(4, uq.getUserQContent());
			pstmt.setInt(5, uq.getUserQNo());
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
	public void delete(int userQNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, userQNo);
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
	public UserQuestion findByPrimaryKey(int userQNo) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		UserQuestion uq = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, userQNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				uq = new UserQuestion();
				uq.setUserQNo(rs.getInt("userQNo"));
				uq.setFaqNo(rs.getInt("faqNo"));
				uq.setUserQCategory(rs.getString("userQCategory"));
				uq.setUserQTitle(rs.getString("userQTitle"));
				uq.setUserQContent(rs.getString("userQContent"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return uq;
	}

	@Override
	public List<UserQuestion> getAll() {
		List<UserQuestion> uqList = new ArrayList<UserQuestion>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserQuestion uq = new UserQuestion();
				uq.setUserQNo(rs.getInt("userQNo"));
				uq.setFaqNo(rs.getInt("faqNo"));
				uq.setUserQCategory(rs.getString("userQCategory"));
				uq.setUserQTitle(rs.getString("userQTitle"));
				uq.setUserQContent(rs.getString("userQContent"));
				uqList.add(uq);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return uqList;
	}

}
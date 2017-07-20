package com.userquestion.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserQuestionDAO implements UserQuestionDAO_Interface {

	private static DataSource ds;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO UserQuestion(userQNO, faqNo, userQCatgory, userQTitle, userQContent)"
											+ " VALUES(USERQUESTION_SQ.NEXTVAL,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE UserQuestion SET userQNO = ?, faqNo, userQCategory = ?, userQTitle = ?, userQContent = ?"
											+ "userQNo = ?, faqNo, userQCategory = ?, userQTitle = ?, userQContent = ? WHERE userQNO = ?";
	private static final String DELETE_STMT = "DELETE FROM UserQuestion WHERE userQNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM uq WHERE faqNO = ?";
	private static final String GET_ALL = "SELECT * FROM UserQuestion";

	@Override
	public void insert(UserQuestion uq) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setInt(1, uq.getUserQNo());
			pstmt.setInt(1, uq.getFaqNo());
			pstmt.setString(2, uq.getUserQCategory());
			pstmt.setString(3, uq.getUserQTitle());
			pstmt.setString(4, uq.getUserQContent());
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
	public void update(UserQuestion uq) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, uq.getUserQNo());
			pstmt.setInt(2, uq.getFaqNo());
			pstmt.setString(3, uq.getUserQCategory());
			pstmt.setString(4, uq.getUserQTitle());
			pstmt.setString(5, uq.getUserQContent());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, userQNo);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
			con = ds.getConnection();
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

		} catch (SQLException e) {
			e.printStackTrace();
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
		List<UserQuestion> uqList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserQuestion uq = new UserQuestion();
				uq.setUserQNo(rs.getInt("UserQNo"));
				uq.setFaqNo(rs.getInt("faqNo"));
				uq.setUserQCategory(rs.getString("UserQCategory"));
				uq.setUserQTitle(rs.getString("UserQTitle"));
				uq.setUserQContent(rs.getString("UserQContent"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
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
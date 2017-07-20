package com.slide.model;

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

public class SlideJDBCDAO implements SlideDAO_Interface {

	final String driver = "oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "petym";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Slide(slideNo, slideImg, slideCategory, slideTitle)"
			+ " VALUES(SLIDE_SQ.NEXTVAL,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE Slide SET slideImg = ?, slideCategory = ?, slideTitle = ? WHERE slideNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Slide WHERE slideNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Slide WHERE slideNO = ?";
	private static final String GET_ALL = "SELECT * FROM Slide";

	@Override
	public void insert(Slide slide) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setBytes(1, slide.getSlideImg());
			pstmt.setString(2, slide.getSlideCategory());
			pstmt.setString(3, slide.getSlideTitle());
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
	public void update(Slide slide) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setBytes(1, slide.getSlideImg());
			pstmt.setString(2, slide.getSlideCategory());
			pstmt.setString(3, slide.getSlideTitle());
			pstmt.setInt(4, slide.getSlideNo());
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
	public void delete(int newsNo) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, newsNo);
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
	public Slide findByPrimaryKey(int slideNo) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		Slide slide = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, slideNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				slide = new Slide();
				slide.setSlideNo(rs.getInt("slideNo"));
				slide.setSlideImg(rs.getBytes("slideImg"));
				slide.setSlideCategory(rs.getString("slideCategory"));
				slide.setSlideTitle(rs.getString("slideTItle"));

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
		return slide;
	}

	@Override
	public List<Slide> getAll() {
		List<Slide> slideList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Slide slide = new Slide();
				slide.setSlideNo(rs.getInt("slideNo"));
				slide.setSlideImg(rs.getBytes("slideImg"));
				slide.setSlideCategory(rs.getString("slideCategory"));
				slide.setSlideTitle(rs.getString("slideTitle"));
				slideList.add(slide);
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
		return slideList;
	}

}
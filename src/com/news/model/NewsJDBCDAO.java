package com.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsJDBCDAO implements NewsDAO_Interface {

	final String driver = "oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "petym";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO News(newsNO, empNo, newsTitle, newsContent, newsDate)"
			+ " VALUES(news_SQ.NEXTVAL,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE News SET newsTitle = ?, newsContent = ?, newsDate = ? WHERE newsNO = ?";
	private static final String DELETE_STMT = "DELETE FROM News WHERE newsNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM News WHERE newsNO = ?";
	private static final String GET_ALL = "SELECT * FROM News";

	@Override
	public void insert(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			銝���身empNo
			pstmt.setInt(1,2);
			pstmt.setString(2, news.getNewsTitle());
			pstmt.setString(3, news.getNewsContent());
			pstmt.setTimestamp(4, news.getNewsDate());
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
	public void update(News news) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, news.getNewsTitle());
			pstmt.setString(2, news.getNewsContent());
			pstmt.setTimestamp(3, news.getNewsDate());
			pstmt.setInt(4, news.getNewsNo());
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
	public News findByPrimaryKey(int newsNo) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		News news = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, newsNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				news = new News();
				news.setNewsNo(rs.getInt("newsNo"));
				news.setEmpNo(rs.getInt("empNo"));
				news.setNewsTitle(rs.getString("newsTitle"));
				news.setNewsContent(rs.getString("newsContent"));
				news.setNewsDate(rs.getTimestamp("newsDate"));

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
		return news;
	}

	@Override
	public List<News> getAll() {
		List<News> newsList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNewsNo(rs.getInt("newsNo"));
				news.setEmpNo(rs.getInt("empNo"));
				news.setNewsTitle(rs.getString("newsTitle"));
				news.setNewsContent(rs.getString("newsContent"));
				news.setNewsDate(rs.getTimestamp("newsDate"));
				newsList.add(news);

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
		return newsList;
	}

}
package com.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NewsDAO implements NewsDAO_Interface{
	
	 private static DataSource ds;
	 
	    static {
	        try {
	            Context ctx = new javax.naming.InitialContext();
	            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static final String INSERT_STMT = "INSERT INTO News(newsNO, empNo, newsTitle, newsContent, newsDate)"
	            								+ " VALUES(newsNO_SQ.NEXTVAL,?,?,?,?,?)";
	    private static final String UPDATE_STMT = "UPDATE News SET newsNO = ?, empNo = ?, newsTitle = ?, newsContent = ?, newsDate = ?"
	            								+ "newsNo = ?, empNo = ?, newsTitle = ?, newsContent = ?, newsDate = ? WHERE newsNO = ?";
	    private static final String DELETE_STMT = "DELETE FROM News WHERE newsNO = ?";
	    private static final String FIND_BY_PK = "SELECT * FROM News WHERE NewsNO = ?";
	    private static final String GET_ALL = "SELECT * FROM News";
	    
	    @Override
	    public void insert(News news) {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	 
	        try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(INSERT_STMT);
	            
	            pstmt.setInt(1, news.getNewsNo());
//	            pstmt.setInt(2, emp.getempNo());
	            pstmt.setString(3, news.getNewsTitle());
	            pstmt.setString(4, news.getNewsContent());
	            pstmt.setTimestamp(5, news.getNewsDate());
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
	        PreparedStatement pstmt=null;
	        Connection con=null;
	         
	        try {
	        	con = ds.getConnection();
	            pstmt = con.prepareStatement(UPDATE_STMT);
	            
	            pstmt.setInt(1, news.getNewsNo());
//	            pstmt.setInt(2, emp.getempNo());
	            pstmt.setString(3, news.getNewsTitle());
	            pstmt.setString(4, news.getNewsContent());
	            pstmt.setTimestamp(5, news.getNewsDate());	            
	            pstmt.executeUpdate();
	 
	        } catch (SQLException e) {
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
	    public void delete(int newsNo) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(DELETE_STMT);
	            pstmt.setInt(1, newsNo);
	            pstmt.executeUpdate();
	 
	        } catch (SQLException e) {
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
	    public News findByPrimaryKey(int newsNo) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	        ResultSet rs=null;
	        News news=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(FIND_BY_PK);
	            pstmt.setInt(1, newsNo);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	                news=new News();
	                news.setNewsNo(rs.getInt("newsNo"));
	                news.setEmpNo(rs.getInt("empNo"));	                
	                news.setNewsTitle(rs.getString("newsTitle"));
	                news.setNewsContent(rs.getString("newsContent"));
	                news.setNewsDate(rs.getTimestamp("newsDate"));
	                	                
	            }
	             
	        } catch (SQLException e) {
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
	        return news;
	    }
	 
	    @Override
	    public List<News> getAll() {
	        List<News> newsList = new ArrayList<>();
	        PreparedStatement pstmt=null;
	        Connection con=null;
	        ResultSet rs=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(GET_ALL);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	            	News news=new News();
	            	news.setNewsNo(rs.getInt("newsNo"));
	                news.setEmpNo(rs.getInt("empNo"));	                
	                news.setNewsTitle(rs.getString("newsTitle"));
	                news.setNewsContent(rs.getString("newsContent"));
	                news.setNewsDate(rs.getTimestamp("newsDate"));
	                      
	            }
	             
	        } catch (SQLException e) {
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
	        return newsList;
	    }

}
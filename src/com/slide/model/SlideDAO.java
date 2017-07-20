package com.slide.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SlideDAO implements SlideDAO_Interface{
	
	 private static DataSource ds;
	 
	    static {
	        try {
	            Context ctx = new javax.naming.InitialContext();
	            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static final String INSERT_STMT = "INSERT INTO Slide(slideNo, slideImg, slideCatgory, slideTitle)"
	            								+ " VALUES(SLIDE_SQ.NEXTVAL,?,?,?,?,?)";
	    private static final String UPDATE_STMT = "UPDATE Slide SET slideNO = ?, slideNo = ?, slideCategory = ?, slideTitle = ?"
	            								+ "slideNo = ?, slideImg = ?, slideCategory = ?, slideTitle = ? WHERE slideNO = ?";
	    private static final String DELETE_STMT = "DELETE FROM Slide WHERE slideNO = ?";
	    private static final String FIND_BY_PK = "SELECT * FROM Slide WHERE slideNO = ?";
	    private static final String GET_ALL = "SELECT * FROM Slide";
	    
	    @Override
	    public void insert(Slide slide) {
	        Connection con = null;
	        PreparedStatement pstmt = null;	        
	 
	        try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(INSERT_STMT);
	            
	            pstmt.setInt(1, slide.getSlideNo());
	            Blob blob = con.createBlob();
				blob.setBytes(2, slide.getSlideImg());
				pstmt.setString(3, slide.getSlideCategory());
				pstmt.setString(4, slide.getSlideTitle());
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
	        PreparedStatement pstmt=null;
	        Connection con=null;
	         
	        try {
	        	con = ds.getConnection();
	            pstmt = con.prepareStatement(UPDATE_STMT);
	            pstmt.setInt(1, slide.getSlideNo());
	            Blob blob = con.createBlob();
				blob.setBytes(2, slide.getSlideImg());
				pstmt.setString(3, slide.getSlideCategory());
				pstmt.setString(4, slide.getSlideTitle());
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
	    public Slide findByPrimaryKey(int slideNo) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	        ResultSet rs=null;
	        Slide slide=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(FIND_BY_PK);
	            pstmt.setInt(1, slideNo);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	            	slide=new Slide();
	                slide.setSlideNo(rs.getInt("slideNo"));
	                slide.setSlideImg(rs.getBytes("slideImg"));	                
	                slide.setSlideTitle(rs.getString("slideTItle"));
	                	                
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
	        return slide;
	    }
	 
	    @Override
	    public List<Slide> getAll() {
	        List<Slide> slideList = new ArrayList<>();
	        PreparedStatement pstmt=null;
	        Connection con=null;
	        ResultSet rs=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(GET_ALL);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	            	Slide slide=new Slide();
	            	slide.setSlideNo(rs.getInt("slideNo"));
	                slide.setSlideImg(rs.getBytes("slideImg"));	                
	                slide.setSlideCategory(rs.getString("slideCatgory"));
	                slide.setSlideTitle(rs.getString("slideTitle"));
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
	        return slideList;
	    }

}
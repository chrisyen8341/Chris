package com.ad.model;

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

public class AdDAO implements AdDAO_Interface{
	
	 private static DataSource ds;
	 
	    static {
	        try {
	            Context ctx = new javax.naming.InitialContext();
	            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static final String INSERT_STMT = "INSERT INTO Ad(adNO, adContent, adImg, adOnline, adOffline, adFee)"
	            								+ " VALUES(adNO_SQ.NEXTVAL,?,?,?,?,?,?)";
	    private static final String UPDATE_STMT = "UPDATE Ad SET adNO = ?, adContent = ?, adImg = ?, adOnline = ?, adOffline = ?, adFee = ?,"
	            								+ "adNo = ?, adContent = ?, adImg = ?, adOnline = ?, adOffline = ?, adFee = ? WHERE adNO = ?";
	    private static final String DELETE_STMT = "DELETE FROM AD WHERE adNO = ?";
	    private static final String FIND_BY_PK = "SELECT * FROM Ad WHERE adNO = ?";
	    private static final String GET_ALL = "SELECT * FROM Ad";
	    
	    @Override
	    public void insert(Ad ad) {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	 
	        try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(INSERT_STMT);
	            
	            pstmt.setInt(1, ad.getAdNo());
	            pstmt.setString(2, ad.getAdContent());
	            Blob blob = con.createBlob();
				blob.setBytes(3, ad.getAdImg());
	            pstmt.setTimestamp(4, ad.getAdOnline());
	            pstmt.setTimestamp(5, ad.getAdOffline());
	            pstmt.setDouble(6, ad.getAdFee());
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
	    public void update(Ad ad) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	         
	        try {
	        	con = ds.getConnection();
	            pstmt = con.prepareStatement(UPDATE_STMT);
	            pstmt.setInt(1, ad.getAdNo());
	            pstmt.setString(2, ad.getAdContent());
	            Blob blob = con.createBlob();
				blob.setBytes(3, ad.getAdImg());
	            pstmt.setTimestamp(4, ad.getAdOnline());
	            pstmt.setTimestamp(5, ad.getAdOffline());
	            pstmt.setDouble(6, ad.getAdFee());
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
	    public void delete(int adNo) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(DELETE_STMT);
	            pstmt.setInt(1, adNo);
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
	    public Ad findByPrimaryKey(int adNo) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	        ResultSet rs=null;
	        Ad ad=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(FIND_BY_PK);
	            pstmt.setInt(1, adNo);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	                ad=new Ad();
	                ad.setAdNo(rs.getInt("adNo"));
	                ad.setAdContent(rs.getString("adContent"));
	                ad.setAdImg(rs.getBytes("adImg"));
	                ad.setAdOnline(rs.getTimestamp("adOnline"));
	                ad.setAdOffline(rs.getTimestamp("adOffline"));
	                ad.setAdFee(rs.getDouble("adFee"));
	                
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
	        return ad;
	    }
	 
	    @Override
	    public List<Ad> getAll() {
	        List<Ad> adList = new ArrayList<>();
	        PreparedStatement pstmt=null;
	        Connection con=null;
	        ResultSet rs=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(GET_ALL);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	            	Ad ad=new Ad();
	                ad.setAdNo(rs.getInt("adNo"));
	                ad.setAdContent(rs.getString("adContent"));
	                ad.setAdImg(rs.getBytes("adImg"));
	                ad.setAdOnline(rs.getTimestamp("adOnline"));
	                ad.setAdOffline(rs.getTimestamp("adOffline"));
	                ad.setAdFee(rs.getDouble("adFee"));      
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
	        return adList;
	    }

}
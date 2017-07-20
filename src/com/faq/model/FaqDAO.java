package com.faq.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FaqDAO implements FaqDAO_Interface{
	
	 private static DataSource ds;
	 
	    static {
	        try {
	            Context ctx = new javax.naming.InitialContext();
	            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static final String INSERT_STMT = "INSERT INTO Faq(faqNO, empNo, faqCategory, faqTitle, faqAnswer)"
	            + " VALUES(faqNO_SQ.NEXTVAL,?,?,?,?)";
	    private static final String UPDATE_STMT = "UPDATE Faq SET faqNO = ?, empNO = ?, faqCategory = ?, faqTitle = ?, faqAnswer = ?"
	            + "faqNo = ?, faqCategory = ?, faqTitle = ?, faqAnswer = ? WHERE faqNO = ?";
	    private static final String DELETE_STMT = "DELETE FROM Faq WHERE faqNO = ?";
	    private static final String FIND_BY_PK = "SELECT * FROM Faq WHERE faqNO = ?";
	    private static final String GET_ALL = "SELECT * FROM Faq";
	    
	    @Override
	    public void insert(Faq faq) {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	 
	        try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(INSERT_STMT);
	            pstmt.setInt(1, faq.getFaqNo());
	            pstmt.setInt(2, faq.getEmpNo());
	            pstmt.setString(3, faq.getFaqCategory());
	            pstmt.setString(4, faq.getFaqTitle());
	            pstmt.setString(5, faq.getFaqAnswer());	            
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
	    public void update(Faq faq) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	         
	        try {
	        	con = ds.getConnection();
	            pstmt = con.prepareStatement(UPDATE_STMT);
	            pstmt.setInt(1, faq.getFaqNo());
	            pstmt.setInt(2, faq.getEmpNo());
	            pstmt.setString(3, faq.getFaqCategory());
	            pstmt.setString(4, faq.getFaqTitle());
	            pstmt.setString(5, faq.getFaqAnswer());	            
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
	    public void delete(int faqNo) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(DELETE_STMT);
	            pstmt.setInt(1, faqNo);
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
	    public Faq findByPrimaryKey(int faqNo) {
	        PreparedStatement pstmt=null;
	        Connection con=null;
	        ResultSet rs=null;
	        Faq faq=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(FIND_BY_PK);
	            pstmt.setInt(1, faqNo);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	                faq=new Faq();
	                faq.setFaqNo(rs.getInt("faqNo"));
	                faq.setFaqCategory(rs.getString("faqCategory"));
	                faq.setFaqTitle(rs.getString("faqTitle"));
	                faq.setFaqAnswer(rs.getString("faqAnswer"));
	                	                
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
	        return faq;
	    }
	 
	    @Override
	    public List<Faq> getAll() {
	        List<Faq> faqList = new ArrayList<>();
	        PreparedStatement pstmt=null;
	        Connection con=null;
	        ResultSet rs=null;
	         
	        try {
	            con=ds.getConnection();
	            pstmt=con.prepareStatement(GET_ALL);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	            	Faq faq=new Faq();
	                faq.setFaqNo(rs.getInt("faqNo"));
	                faq.setFaqCategory(rs.getString("faqCategory"));
	                faq.setFaqTitle(rs.getString("faqTitle"));
	                faq.setFaqAnswer(rs.getString("faqAnswer"));
	                      
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
	        return faqList;
	    }

}
package com.dateitem.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Clob;

public class DateItemDAO implements DateItemDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO DATEITEM(DATEITEMNO,SELLERNO,RESTLISTNO,"
			+ "DATEITEMTITLE,DATEITEMIMG,DATEITEMTEXT,DATEITEMTIME,DATEMEETINGTIME,DATEITEMLOCATE,"
			+ "DATEITEMPEOPLE,HASMATE,DATEITEMPRICE,DATEITEMSTATUS,DATEITEMSHOW,DATEITEMVIEWER,BUYERNO,"
			+ "ISQRCCHECKED,BUYERREP,SELLERREP,ISINSTANTDATE)"
			+ " VALUES(DATEITEMNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE DATEITEM SET DATEITEMNO = ?, SELLERNO = ?, RESTLISTNO = ?, "
			+ "DATEITEMTITLE = ?, DATEITEMIMG = ?, DATEITEMTEXT = ?, DATEITEMTIME = ?, DATEMEETINGTIME = ?, "
			+ "DATEITEMLOCATE = ? ,DATEITEMPEOPLE = ? , HASMATE =?, DATEITEMPRICE =? , DATEITEMSTATUS=? ,"
			+ "DATEITEMSHOW=?, DATEITEMVIEWER=?, BUYERNO=?, ISQRCCHECKED=?, BUYERREP=? , SELLERREP=?, ISINSTANTDATE=? WHERE DATEITEMNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM DATEITEM WHERE DATEITEMNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM DATEITEM WHERE DATEITEMNO = ?";
	private static final String GET_ALL = "SELECT * FROM DATEITEM";
	
	
	
	@Override
	public void add(DateItemVO dateItemVO) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, dateItemVO.getSellerNo());
			pstmt.setInt(2, dateItemVO.getRestListNo());
			pstmt.setString(3, dateItemVO.getDateItemTitle());
			Blob blob=con.createBlob();
			blob.setBytes(1, dateItemVO.getDateItemImg());
			pstmt.setBlob(4, blob);
			Clob clob=con.createClob();
			clob.setString(1, dateItemVO.getDateItemText());
			pstmt.setClob(5, clob);
			pstmt.setDate(6, dateItemVO.getDateItemTime());
			pstmt.setDate(7, dateItemVO.getDateMeetingTime());
			pstmt.setString(8, dateItemVO.getDateItemLocate());
			pstmt.setInt(9, dateItemVO.getDateItemPeople());
			pstmt.setBoolean(10, dateItemVO.getHasMate());
			pstmt.setInt(11, dateItemVO.getDateItemPrice());
			pstmt.setInt(12, dateItemVO.getDateItemStatus());
			pstmt.setInt(13, dateItemVO.getDateItemShow());
			pstmt.setInt(14, dateItemVO.getDateItemViewer());
			pstmt.setInt(15, dateItemVO.getBuyerNo());
			pstmt.setBoolean(16, dateItemVO.getIsQRCChecked());
			pstmt.setInt(17, dateItemVO.getBuyerRep());
			pstmt.setInt(18, dateItemVO.getSellerRep());
			pstmt.setBoolean(19, dateItemVO.getIsInstantDate());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void update(DateItemVO dateItemVO) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, dateItemVO.getDateItemNo());
			pstmt.setInt(2, dateItemVO.getSellerNo());
			pstmt.setInt(3, dateItemVO.getRestListNo());
			pstmt.setString(4, dateItemVO.getDateItemTitle());
			Blob blob=con.createBlob();
			blob.setBytes(1, dateItemVO.getDateItemImg());
			pstmt.setBlob(5, blob);
			Clob clob=con.createClob();
			clob.setString(1, dateItemVO.getDateItemText());
			pstmt.setClob(6, clob);
			pstmt.setDate(7, dateItemVO.getDateItemTime());
			pstmt.setDate(8, dateItemVO.getDateMeetingTime());
			pstmt.setString(9, dateItemVO.getDateItemLocate());
			pstmt.setInt(10, dateItemVO.getDateItemPeople());
			pstmt.setBoolean(11, dateItemVO.getHasMate());
			pstmt.setInt(12, dateItemVO.getDateItemPrice());
			pstmt.setInt(13, dateItemVO.getDateItemStatus());
			pstmt.setInt(14, dateItemVO.getDateItemShow());
			pstmt.setInt(15, dateItemVO.getDateItemViewer());
			pstmt.setInt(16, dateItemVO.getBuyerNo());
			pstmt.setBoolean(17, dateItemVO.getIsQRCChecked());
			pstmt.setInt(18, dateItemVO.getBuyerRep());
			pstmt.setInt(19, dateItemVO.getSellerRep());
			pstmt.setBoolean(20, dateItemVO.getIsInstantDate());
			pstmt.setInt(21, dateItemVO.getDateItemNo());
			pstmt.executeUpdate();

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void delete(int dateItemNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, dateItemNo);
			pstmt.executeUpdate();

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public DateItemVO findByPk(int dateItemNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		DateItemVO dateItemVO=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, dateItemNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getDate("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getDate("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));		
				
			}
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return dateItemVO;
	}

	@Override
	public List<DateItemVO> getAll() {
		List<DateItemVO> dateItemList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DateItemVO dateItemVO=new DateItemVO();
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getDate("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getDate("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));	
				dateItemList.add(dateItemVO);		
			}
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return dateItemList;
	}
	
}

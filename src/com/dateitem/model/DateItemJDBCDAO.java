package com.dateitem.model;

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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.Clob;

public class DateItemJDBCDAO implements DateItemDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "petym";
	private static final String PASSWORD = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO DATEITEM(DATEITEMNO,SELLERNO,RESTLISTNO,"
			+ "DATEITEMTITLE,DATEITEMIMG,DATEITEMTEXT,DATEITEMTIME,DATEMEETINGTIME,DATEITEMLOCATE,"
			+ "DATEITEMPEOPLE,HASMATE,DATEITEMPRICE,DATEITEMSTATUS,DATEITEMSHOW,DATEITEMVIEWER,BUYERNO,"
			+ "ISQRCCHECKED,BUYERREP,SELLERREP,ISINSTANTDATE,PETNO)"
			+ " VALUES(DATEITEMNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE DATEITEM SET DATEITEMNO = ?, SELLERNO = ?, RESTLISTNO = ?, "
			+ "DATEITEMTITLE = ?, DATEITEMIMG = ?, DATEITEMTEXT = ?, DATEITEMTIME = ?, DATEMEETINGTIME = ?, "
			+ "DATEITEMLOCATE = ? ,DATEITEMPEOPLE = ? , HASMATE =?, DATEITEMPRICE =? , DATEITEMSTATUS=? ,"
			+ "DATEITEMSHOW=?, DATEITEMVIEWER=?, BUYERNO=?, ISQRCCHECKED=?, BUYERREP=? , SELLERREP=? ,ISINSTANTDATE=? , PETNO=? WHERE DATEITEMNO =　?";
	private static final String DELETE_STMT = "DELETE FROM DATEITEM WHERE DATEITEMNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM DATEITEM WHERE DATEITEMNO = ?";
	private static final String GET_ALL = "SELECT * FROM DATEITEM";
	
	
	
	@Override
	public void add(DateItemVO dateItemVO) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, dateItemVO.getSellerNo());
			pstmt.setInt(2, dateItemVO.getRestListNo());
			pstmt.setString(3, dateItemVO.getDateItemTitle());
//			Blob blob=con.createBlob();
//			blob.setBytes(1, dateItemVO.getDateItemImg());
//			pstmt.setBlob(4, blob);
			pstmt.setBytes(4, dateItemVO.getDateItemImg());
			
//			Clob clob=con.createClob();
//			clob.setString(1, dateItemVO.getDateItemText());
//			pstmt.setClob(5, clob);
			pstmt.setString(5, dateItemVO.getDateItemText());
			pstmt.setTimestamp(6, dateItemVO.getDateItemTime());
			pstmt.setTimestamp(7, dateItemVO.getDateMeetingTime());
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
			pstmt.setInt(20, dateItemVO.getPetNo());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
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
			pstmt.setTimestamp(7, dateItemVO.getDateItemTime());
			pstmt.setTimestamp(8, dateItemVO.getDateMeetingTime());
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
			pstmt.setInt(21,dateItemVO.getPetNo());
			pstmt.setInt(22, dateItemVO.getDateItemNo());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void delete(int dateItemNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, dateItemNo);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public DateItemVO findByPk(int dateItemNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		DateItemVO dateItemVO=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, dateItemNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dateItemVO =new DateItemVO();	
				
				dateItemVO.setDateItemNo(rs.getInt("dateItemNo"));
				dateItemVO.setSellerNo(rs.getInt("sellerNo"));
				dateItemVO.setRestListNo(rs.getInt("restListNo"));
				dateItemVO.setDateItemTitle(rs.getString("dateItemTitle"));
				dateItemVO.setDateItemImg(rs.getBytes("dateItemImg"));
				dateItemVO.setDateItemText(rs.getString("dateItemText"));
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
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
				dateItemVO.setDateItemTime(rs.getTimestamp("dateItemTime"));
				dateItemVO.setDateMeetingTime(rs.getTimestamp("dateMeetingTime"));
				dateItemVO.setDateItemLocate(rs.getString("dateItemLocate"));
				dateItemVO.setDateItemPeople(rs.getInt("dateItemPeople"));
				dateItemVO.setHasMate(rs.getBoolean("hasMate"));
				dateItemVO.setDateItemPrice(rs.getInt("dateItemPrice"));
				dateItemVO.setDateItemStatus(rs.getInt("dateItemStatus"));
				dateItemVO.setDateItemShow(rs.getInt("dateItemShow"));
				dateItemVO.setDateItemViewer(rs.getInt("dateItemShow"));
				dateItemVO.setBuyerNo(rs.getInt("buyerNo"));
				dateItemVO.setIsQRCChecked(rs.getBoolean("isQRCChecked"));
				dateItemVO.setBuyerRep(rs.getInt("buyerRep"));
				dateItemVO.setSellerRep(rs.getInt("SellerRep"));
				dateItemVO.setIsInstantDate(rs.getBoolean("isInstantDate"));
				dateItemVO.setPetNo(rs.getInt("petNo"));
				dateItemList.add(dateItemVO);		
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	
	// Main方法
	public static void main(String [ ] args) throws IOException{
		byte[] testImg = getPictureByteArray("C:\\Users\\PSP\\Desktop\\bh.png");		
		DateItemJDBCDAO dao=new DateItemJDBCDAO();
		DateItemVO dateItemVO = new DateItemVO();
		
		

	
		dateItemVO.setSellerNo(5001);
		dateItemVO.setRestListNo(7001);
		dateItemVO.setDateItemTitle("陽光午後約會大好");
		dateItemVO.setDateItemImg(testImg);
		dateItemVO.setDateItemText("22來進行一場午後的約會吧測試測試");
		
		GregorianCalendar cal = new GregorianCalendar(2017,07,31,18,30,59);
		java.util.Date ud = cal.getTime();
		Timestamp ts= new Timestamp(ud.getTime());
		
		dateItemVO.setDateItemTime(ts);
		dateItemVO.setDateMeetingTime(ts);	
		dateItemVO.setDateItemLocate("台北市");
		dateItemVO.setDateItemPeople(0);
		dateItemVO.setHasMate(false);
		dateItemVO.setDateItemPrice(1500);
		dateItemVO.setDateItemStatus(1);
		dateItemVO.setDateItemShow(0);
		dateItemVO.setDateItemViewer(1600);
		dateItemVO.setBuyerNo(5002);
		dateItemVO.setIsQRCChecked(false);
		dateItemVO.setBuyerRep(0);
		dateItemVO.setSellerRep(0);
		dateItemVO.setIsInstantDate(false);
		dateItemVO.setPetNo(1001);
		
		//新增
		dao.add(dateItemVO);		
		System.out.println("已新增第1筆");
//		dao.add(dateItemVO);
//		System.out.println("已新增第2筆");
//		dao.add(dateItemVO);
//		System.out.println("已新增第3筆");
//		
//		//修改
//		dateItemVO.setDateItemNo(4002);
//		dateItemVO.setDateItemTitle("已修改已修改過了");	
//		dao.update(dateItemVO);
//		System.out.println("已修改第二筆");
		
//		//刪除
//		dao.delete(4001);
//		System.out.println("已刪除4001");
//		
//		//查詢 by PK
//
//		
//		
		dateItemVO = dao.findByPk(4006);
		dateItemVO.setDateItemTime(ts);
		System.out.println(dateItemVO.getDateItemLocate());
		System.out.println(dateItemVO.getDateItemText());
		System.out.println(dateItemVO.getDateItemNo());
		System.out.println(dateItemVO.getDateItemPrice());
		System.out.println(dateItemVO.getDateItemTime());
		System.out.println(dateItemVO.getDateMeetingTime());
		
//		
//		//getAll
//		List<DateItemVO> dateItemList = dao.getAll();
//		for(DateItemVO dateItem : dateItemList){
//		System.out.println(dateItem.getDateItemNo());
//		System.out.println(dateItem.getDateItemTitle());
//		System.out.println(dateItem.getDateItemPrice());
//		System.out.println(dateItem.getSellerNo());
//		System.out.println(dateItem.getDateItemShow());
//		System.out.println(dateItem.getDateItemText());
//		System.out.println(dateItem.getRestListNo());
//		System.out.println(dateItem.getDateMeetingTime());
//		System.out.println(dateItem.getDateItemTime());
//		System.out.println(dateItem.getIsInstantDate());
//	}
		
		
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	@Override
	public List<DateItemVO> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DateItemVO> findBySeller_future(int sellerNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DateItemVO> findBySeller_history(int sellerNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DateItemVO> findBySeller_onsale(int sellerNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DateItemVO> findByBuyer_future(int buyerNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DateItemVO> findByBuyer_history(int buyerNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateByVO(DateItemVO dateItemVO) {
		// TODO Auto-generated method stub
		
	}
	
}



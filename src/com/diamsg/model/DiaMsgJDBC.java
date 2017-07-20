package com.diamsg.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class DiaMsgJDBC implements DiaMsgDAO_Interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "petym";
	String passwd = "123456";
	
	private static final String INSERT = 
			"INSERT INTO DIAMSG(DIAMSGNO, DIANO, MEMNO, DIAMSGTEXT, DIAMSGDATE, DIAMSGSTATE) VALUES(DIAMSG_SEQ.NEXTVAL ,?,?,?,?,?)";
	private static final String GETALL = 
			"SELECT DIAMSGNO, DIANO, MEMNO, DIAMSGTEXT, DIAMSGDATE, DIAMSGSTATE FROM DIAMSG ORDER BY DIAMSGDATE";
	private static final String GETONE =
			"SELECT DIAMSGNO, DIANO, MEMNO, DIAMSGTEXT, DIAMSGDATE, DIAMSGSTATE FROM DIAMSG WHERE DIAMSGNO = ? ";
	private static final String UPDATE =
			"UPDATE DIAMSG SET DIAMSGTEXT=?, DIAMSGDATE=? WHERE DIAMSGNO=?";
	private static final String DELETE=
			"DELETE FROM DIAMSG WHERE DIAMSGNO=?";
	
	@Override
	public void insert(DiaMsg diaMsg) {
		
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT);			
			
			pstmt.setInt(1, diaMsg.getDiaNO());
			pstmt.setInt(2, diaMsg.getMemNo());
			pstmt.setString(3, diaMsg.getDiaMsgText());
			pstmt.setTimestamp(4, diaMsg.getDiaMsgTime());
			pstmt.setInt(5, diaMsg.getDiaMsgState());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
				
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(DiaMsg diaMsg) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			
			pstmt = con.prepareStatement(UPDATE);	
			
			
			pstmt.setString(1, diaMsg.getDiaMsgText());
			pstmt.setTimestamp(2, diaMsg.getDiaMsgTime());
			pstmt.setInt(3, diaMsg.getDiaMsgNO());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
				
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(Integer diaMsgNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, diaMsgNo);
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public DiaMsg findByPrimaryKey(Integer diaMsgNo) {
		
		DiaMsg diaMsg = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETONE);

			pstmt.setInt(1, diaMsgNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				diaMsg = new DiaMsg();
				diaMsg.setDiaMsgNO(rs.getInt("diamsgno"));
				System.out.println(rs.getInt("diamsgno"));
				diaMsg.setDiaNO(rs.getInt("diano"));
				diaMsg.setMemNo(rs.getInt("memno"));
				diaMsg.setDiaMsgText(rs.getString("diamsgtext"));
				diaMsg.setDiaMsgTime(rs.getTimestamp("diamsgdate"));
				diaMsg.setDiaMsgState(rs.getInt("diamsgstate"));
				
			}
			
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return diaMsg;
	}

	@Override
	public List<DiaMsg> getAll() {
		
	
		List<DiaMsg> list = new ArrayList<DiaMsg>();
		DiaMsg diaMsg = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {
				
				diaMsg = new DiaMsg();
				diaMsg.setDiaMsgNO(rs.getInt("diamsgno"));
				diaMsg.setDiaNO(rs.getInt("diano"));
				diaMsg.setMemNo(rs.getInt("memno"));
				diaMsg.setDiaMsgText(rs.getString("diamsgtext"));
				diaMsg.setDiaMsgTime(rs.getTimestamp("diamsgdate"));
				diaMsg.setDiaMsgState(rs.getInt("diamsgstate"));
				list.add(diaMsg);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			
		return list;
	}
	
	public static void main(String[] args) {
		
		DiaMsgJDBC dao =new DiaMsgJDBC();
		
		//insert
//		DiaMsg diamsg = new DiaMsg();
//		diamsg.setDiaNO(102);
//		diamsg.setMemNo(222);
//		diamsg.setDiaMsgText("«¢«¢¦n¦n¬Ý");
//		diamsg.setDiaMsgTime(new java.sql.Timestamp(System.currentTimeMillis()));
//		diamsg.setDiaMsgState(0);
//		
//		dao.insert(diamsg);
		
		
		//update
//		DiaMsg diamsg1 = new DiaMsg();
//		
//		diamsg1.setDiaMsgText("«¢«¢«¢«¢«¢«¢");
//		diamsg1.setDiaMsgTime(new java.sql.Timestamp(System.currentTimeMillis()));
//		diamsg1.setDiaMsgNO(20018);
//		
//		dao.update(diamsg1);
//		
		//delete
//		dao.delete(20020);
		
		
		//query one
//		DiaMsg diamsg = dao.findByPrimaryKey(20003);
//		System.out.print(diamsg.getDiaMsgNO()+" ,");
//		System.out.print(diamsg.getDiaNO()+" ,");
//		System.out.print(diamsg.getMemNo()+" ,");
//		System.out.print(diamsg.getDiaMsgText()+" ,");
//		System.out.print(diamsg.getDiaMsgTime()+" ,");
//		System.out.print(diamsg.getDiaMsgState());
//		System.out.println("---------------------------------");
		
		
		//query all
		List<DiaMsg> list = dao.getAll();
		for(DiaMsg diamsg:list){
			System.out.print(diamsg.getDiaMsgNO()+" ,");
			System.out.print(diamsg.getDiaNO()+" ,");
			System.out.print(diamsg.getMemNo()+" ,");
			System.out.print(diamsg.getDiaMsgText()+" ,");
			System.out.print(diamsg.getDiaMsgTime()+" ,");
			System.out.print(diamsg.getDiaMsgState());
			System.out.println("---------------------------------");
		}
		
		
	}
	
	
	
	
	
	
}

	
	
	
	
	
	

package com.restMember.model;


	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	import javax.naming.Context;
	import javax.naming.NamingException;
	import javax.sql.DataSource;
public class RestMemberJDBCDAO implements RestMemberDAO_Interface{
	



	

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "petym";
		String passwd = "123456";
		private static final String INSERT_RESTMEMBER = "INSERT INTO RESTMEMBER (RESTMEMID,RESTNO,RESTMEMPSW) VALUES(?,?,?)";
		private static final String UPDATE_RESTMEMBER = "UPDATE RESTMEMBER SET RESTNO=?,RESTMEMPSW=? WHERE RESTMEMID=?";			
		private static final String DELETE_RESTMEMBER = "DELETE FROM RESTMEMBER WHERE RESTMEMID=?";
		private static final String FIND_BY_PK = "SELECT * FROM RESTMEMBER WHERE RESTMEMID=?";
		private static final String GET_ALL = "SELECT * FROM RESTMEMBER";
		@Override
		public void add(RestMember restMember) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, userid, passwd);	
				pstmt = conn.prepareStatement(INSERT_RESTMEMBER);
				pstmt.setString(1, restMember.getRestMemId());
				pstmt.setInt(2, restMember.getRestNo());
				pstmt.setString(3, restMember.getRestMemPsw());
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		@Override
		public void update(RestMember restMember) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, userid, passwd);
				pstmt = conn.prepareStatement(UPDATE_RESTMEMBER);
				pstmt.setInt(1, restMember.getRestNo());
				pstmt.setString(2, restMember.getRestMemPsw());
				pstmt.setString(3, restMember.getRestMemId());
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		@Override
		public void delete(String restMemId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, userid, passwd);
				pstmt = conn.prepareStatement(DELETE_RESTMEMBER);
				pstmt.setString(1, restMemId);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		@Override
		public RestMember findByPK(String restMemId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			RestMember restMember = null ;
			ResultSet rs = null;
			try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, restMemId);
			rs = pstmt.executeQuery();
				while(rs.next()){
					restMember = new RestMember();
					restMember.setRestMemId(rs.getString("RESTMEMID"));
					restMember.setRestNo(rs.getInt("RESTNO"));
					restMember.setRestMemPsw(rs.getString("RESTMEMPSW"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return restMember;
		}

		@Override
		public List<RestMember> getAll() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			RestMember restMember = null ;
			ResultSet rs = null;
			List<RestMember> restMemberList = new ArrayList<RestMember>();
			try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
				while(rs.next()){
					restMember = new RestMember();
					restMember.setRestMemId(rs.getString("RESTMEMID"));
					restMember.setRestNo(rs.getInt("RESTNO"));
					restMember.setRestMemPsw(rs.getString("RESTMEMPSW"));
					restMemberList.add(restMember);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return restMemberList;
		}
		
	
	
	public static void main(String[] args) throws IOException {
		RestMemberJDBCDAO restMemberJDBCDAO = new RestMemberJDBCDAO();
		RestMember restMember = new RestMember();
		
//		restMember.setRestMemId("餐會帳號3");
//		restMember.setRestNo(2);
//		restMember.setRestMemPsw("餐會密碼2");
//		restMemberJDBCDAO.add(restMember);
		
//		restMember.setRestMemId("餐會帳號2");
//		restMember.setRestNo(2);
//		restMember.setRestMemPsw("餐會密碼222");
//		restMemberJDBCDAO.update(restMember);
		
//		restMemberJDBCDAO.delete("餐會帳號3");
		
//		restMember = restMemberJDBCDAO.findByPK("餐會帳號2");
//		System.out.println(restMember.getRestMemId());
//		System.out.println(restMember.getRestNo());
//		System.out.println(restMember.getRestMemPsw());
		
//		List<RestMember> restMemberList = restMemberJDBCDAO.getAll();
//		for(RestMember restMemberListE : restMemberList){
//			System.out.println(restMemberListE.getRestMemId());
//			System.out.println(restMemberListE.getRestNo());
//			System.out.println(restMemberListE.getRestMemPsw());
//		}
	}

	
	
	
}

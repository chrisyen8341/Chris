package com.restMember.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class RestMemberDAO implements RestMemberDAO_Interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/petym");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static final String INSERT_RESTMEMBER = "INSERT INTO RESTMEMBER (RESTMEMID,RESTNO,RESTMEMPSW VALUES(?,?,?)";
	private static final String UPDATE_RESTMEMBER = "UPDATE RESTMEMBER SET RESTNO=?,RESTMEMPSW=? WHERE RESTMEMID=?";			
	private static final String DELETE_RESTMEMBER = "DELETE FROM RESTMEMBER WHERE RESTMEMID=?";
	private static final String FIND_BY_PK = "SELECT * FROM RESTMEMBER WHERE RESTMEMID=?";
	private static final String GET_ALL = "SELECT * FROM RESTMEMBER";
	@Override
	public void add(RestMember restMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_RESTMEMBER);
			pstmt.setString(1, restMember.getRestMemId());
			pstmt.setInt(2, restMember.getRestNo());
			pstmt.setString(3, restMember.getRestMemPsw());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
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
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_RESTMEMBER);
			pstmt.setInt(1, restMember.getRestNo());
			pstmt.setString(2, restMember.getRestMemPsw());
			pstmt.setString(3, restMember.getRestMemId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
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
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_RESTMEMBER);
			pstmt.setString(1, restMemId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
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
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(FIND_BY_PK);
		pstmt.setString(1, restMemId);
		rs = pstmt.executeQuery();
			while(rs.next()){
				restMember = new RestMember();
				restMember.setRestMemId(rs.getString("RESTMEMID"));
				restMember.setRestNo(rs.getInt("RESTNO"));
				restMember.setRestMemPsw(rs.getString("RESTMEMPSW"));
			}
		} catch (SQLException e) {
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
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(GET_ALL);
		rs = pstmt.executeQuery();
			while(rs.next()){
				restMember = new RestMember();
				restMember.setRestMemId(rs.getString("RESTMEMID"));
				restMember.setRestNo(rs.getInt("RESTNO"));
				restMember.setRestMemPsw(rs.getString("RESTMEMPSW"));
				restMemberList.add(restMember);
			}
		} catch (SQLException e) {
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
	
}

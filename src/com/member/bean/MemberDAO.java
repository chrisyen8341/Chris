package com.member.bean;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_Interface{

	private int currSeq;
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER(MEMNO, MEMID, MEMPWD, MEMNAME, MEMSNAME, MEMGENDER,MEMIDNO,MEMBDAY,MEMPHONE,MEMADDRESS,MEMEMAIL"
			+ ",MEMIMG,MEMREPORTED,MEMSTATUS,MEMRELATION,MEMSELFINTRO,MEMFOLLOWED,MEMPOINT,MEMSALERANK,MEMLONGTITUDE,MEMLATITUDE,MEMLOCTIME,MEMLOCSTATUS)"
			+ " VALUES(MEMNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_CURRSEQ = "SELECT MEMNO_SQ.CURRVAL FROM DUAL";
	
	@Override
	public void add(com.member.bean.Member member) {   
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {

			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPwd());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getMemSname());
			pstmt.setString(5, member.getMemGender());
			pstmt.setString(6, member.getMemIdNo());
			pstmt.setDate(7, member.getMemBday());
			pstmt.setString(8, member.getMemPhone());
			pstmt.setString(9, member.getMemAddress());
			pstmt.setString(10, member.getMemEmail());
			Blob blob=con.createBlob();
			blob.setBytes(1,member.getMemImg());
			pstmt.setBlob(11, blob);
			pstmt.setInt(12, member.getMemReported());
			pstmt.setInt(13, member.getMemStatus());
			pstmt.setString(14, member.getMemRelation());
			pstmt.setString(15, member.getMemSelfintro());
			pstmt.setInt(16, member.getMemFollowed());
			pstmt.setInt(17, member.getMemPoint());
			pstmt.setInt(18, member.getMemSaleRank());
			pstmt.setDouble(19, member.getMemLongtitude());
			pstmt.setDouble(20, member.getMemLatitude());
			pstmt.setTimestamp(21, member.getMemLocTime());
			pstmt.setInt(22, member.getMemLocStatus());
			pstmt.executeUpdate();
			
			pstmt2 = con.prepareStatement(GET_CURRSEQ);
			ResultSet rs2=pstmt2.executeQuery();
			rs2.next();
			currSeq = rs2.getInt(1);

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
	public void update(com.member.bean.Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Member(int memno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public com.member.bean.Member findByPk(int memno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.member.bean.Member> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCurrSeq(){
		return currSeq;
	}

}

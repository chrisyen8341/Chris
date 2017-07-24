package com.member.model;

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

import com.pet.model.Pet;
import com.pet.model.PetJDBCDAO;

public class MemberDAO implements MemberDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private int currSeq;
	private static final String INSERT_STMT = "INSERT INTO MEMBER(MEMNO, MEMID, MEMPWD, MEMNAME, MEMSNAME, MEMGENDER,MEMIDNO,MEMBDAY,MEMPHONE,MEMADDRESS,MEMEMAIL"
			+ ",MEMIMG,MEMREPORTED,MEMSTATUS,MEMRELATION,MEMSELFINTRO,MEMFOLLOWED,MEMPOINT,MEMSALERANK,MEMLONGTITUDE,MEMLATITUDE,MEMLOCTIME,MEMLOCSTATUS)"
			+ " VALUES(MEMNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE MEMBER SET MEMNO = ?, MEMID = ?, MEMPWD = ?, MEMNAME = ?, MEMSNAME = ?, MEMGENDER = ?, MEMIDNO = ?, MEMBDAY = ?, MEMPHONE = ?, MEMADDRESS = ?,MEMEMAIL = ? "
			+ ",MEMIMG = ?, MEMREPORTED = ?, MEMSTATUS = ?, MEMRELATION = ?, MEMSELFINTRO = ?, MEMFOLLOWED = ?, MEMPOINT = ?, MEMSALERANK = ?, MEMLONGTITUDE = ?, MEMLATITUDE = ?, MEMLOCTIME = ?, MEMLOCSTATUS = ? WHERE MEMNO = ?";
	private static final String DELETE_STMT = "DELETE FROM MEMBER WHERE MEMNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM MEMBER WHERE MEMNO = ?";
	private static final String FIND_PETS_BY_MEMNO = "SELECT * FROM PET WHERE MEMNO = ? ORDER BY PETNO DESC";
	private static final String GET_ALL = "SELECT * FROM MEMBER";
	private static final String FIND_BY_ID = "SELECT * FROM MEMBER WHERE MEMID = ?";
	private static final String GET_CURRSEQ = "SELECT MEMNO_SQ.CURRVAL FROM DUAL";

	@Override
	public void add(Member member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPwd());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getMemSname());
			pstmt.setInt(5, member.getMemGender());
			pstmt.setString(6, member.getMemIdNo());
			pstmt.setDate(7, member.getMemBday());
			pstmt.setString(8, member.getMemPhone());
			pstmt.setString(9, member.getMemAddress());
			pstmt.setString(10, member.getMemEmail());
			Blob blob = con.createBlob();
			blob.setBytes(1, member.getMemImg());
			pstmt.setBlob(11, blob);
			pstmt.setInt(12, member.getMemReported());
			pstmt.setInt(13, member.getMemStatus());
			pstmt.setInt(14, member.getMemRelation());
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
			ResultSet rs2 = pstmt2.executeQuery();
			rs2.next();
			currSeq = rs2.getInt(1);

		} catch (SQLException se) {
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
	public void addWithPet(Member member, Pet pet) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增會原
			String cols[] = {"MEMNO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPwd());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getMemSname());
			pstmt.setInt(5, member.getMemGender());
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
			pstmt.setInt(14, member.getMemRelation());
			pstmt.setString(15, member.getMemSelfintro());
			pstmt.setInt(16, member.getMemFollowed());
			pstmt.setInt(17, member.getMemPoint());
			pstmt.setInt(18, member.getMemSaleRank());
			pstmt.setDouble(19, member.getMemLongtitude());
			pstmt.setDouble(20, member.getMemLatitude());
			pstmt.setTimestamp(21, member.getMemLocTime());
			pstmt.setInt(22, member.getMemLocStatus());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_memno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_memno = rs.getString(1);
				System.out.println("自增主鍵值= " + next_memno +"(剛新增成功的會員編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			PetJDBCDAO dao = new PetJDBCDAO();
			pet.setMemNo(new Integer(next_memno)) ;
			dao.add2(pet,con);
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
		}  

		catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		catch(Exception e){
			System.out.println("有錯");
		}
		finally {
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
	public void update(Member member) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, member.getMemNo());
			pstmt.setString(2, member.getMemId());
			pstmt.setString(3, member.getMemPwd());
			pstmt.setString(4, member.getMemName());
			pstmt.setString(5, member.getMemSname());
			pstmt.setInt(6, member.getMemGender());
			pstmt.setString(7, member.getMemIdNo());
			pstmt.setDate(8, member.getMemBday());
			pstmt.setString(9, member.getMemPhone());
			pstmt.setString(10, member.getMemAddress());
			pstmt.setString(11, member.getMemEmail());
			Blob blob = con.createBlob();
			blob.setBytes(1, member.getMemImg());
			pstmt.setBlob(12, blob);
			pstmt.setInt(13, member.getMemReported());
			pstmt.setInt(14, member.getMemStatus());
			pstmt.setInt(15, member.getMemRelation());
			pstmt.setString(16, member.getMemSelfintro());
			pstmt.setInt(17, member.getMemFollowed());
			pstmt.setInt(18, member.getMemPoint());
			pstmt.setInt(19, member.getMemSaleRank());
			pstmt.setDouble(20, member.getMemLongtitude());
			pstmt.setDouble(21, member.getMemLatitude());
			pstmt.setTimestamp(22, member.getMemLocTime());
			pstmt.setInt(23, member.getMemLocStatus());
			pstmt.setInt(24, member.getMemNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 多個TABLE時刪不動，MEMBER是多個TABLE的參照，除非先把所有有關連之TABLE刪掉
	@Override
	public void delete(Integer memno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, memno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Member findByPk(Integer memno) {
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setMemNo(rs.getInt("memNo"));
				member.setMemId(rs.getString("memId"));
				member.setMemPwd(rs.getString("memPwd"));
				member.setMemName(rs.getString("memName"));
				member.setMemSname(rs.getString("memSname"));
				member.setMemGender(rs.getInt("memGender"));
				member.setMemIdNo(rs.getString("memIdNo"));
				member.setMemBday(rs.getDate("memBday"));
				member.setMemPhone(rs.getString("memPhone"));
				member.setMemAddress(rs.getString("memAddress"));
				member.setMemEmail(rs.getString("memEmail"));
				member.setMemImg(rs.getBytes("memImg"));
				member.setMemReported(rs.getInt("memReported"));
				member.setMemStatus(rs.getInt("memStatus"));
				member.setMemRelation(rs.getInt("memRelation"));
				member.setMemSelfintro(rs.getString("memSelfintro"));
				member.setMemFollowed(rs.getInt("memFollowed"));
				member.setMemPoint(rs.getInt("memPoint"));
				member.setMemSaleRank(rs.getInt("memSaleRank"));
				member.setMemLongtitude(rs.getDouble("memLongtitude"));
				member.setMemLatitude(rs.getDouble("memLatitude"));
				member.setMemLocTime(rs.getTimestamp("memLocTime"));
				member.setMemLocStatus(rs.getInt("memLocStatus"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return member;
	}

	@Override
	public List<Member> getAll() {
		List<Member> memList = new ArrayList<>();
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setMemNo(rs.getInt("memNo"));
				member.setMemId(rs.getString("memId"));
				member.setMemPwd(rs.getString("memPwd"));
				member.setMemName(rs.getString("memName"));
				member.setMemSname(rs.getString("memSname"));
				member.setMemGender(rs.getInt("memGender"));
				member.setMemIdNo(rs.getString("memIdNo"));
				member.setMemBday(rs.getDate("memBday"));
				member.setMemPhone(rs.getString("memPhone"));
				member.setMemAddress(rs.getString("memAddress"));
				member.setMemEmail(rs.getString("memEmail"));
				member.setMemImg(rs.getBytes("memImg"));
				member.setMemReported(rs.getInt("memReported"));
				member.setMemStatus(rs.getInt("memStatus"));
				member.setMemRelation(rs.getInt("memRelation"));
				member.setMemSelfintro(rs.getString("memSelfintro"));
				member.setMemFollowed(rs.getInt("memFollowed"));
				member.setMemPoint(rs.getInt("memPoint"));
				member.setMemSaleRank(rs.getInt("memSaleRank"));
				member.setMemLongtitude(rs.getDouble("memLongtitude"));
				member.setMemLatitude(rs.getDouble("memLatitude"));
				member.setMemLocTime(rs.getTimestamp("memLocTime"));
				member.setMemLocStatus(rs.getInt("memLocStatus"));
				memList.add(member);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memList;
	}

	public int getCurrSeq() {
		return currSeq;
	}

	@Override
	public Member findById(String memId) {
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ID);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new Member();
				member.setMemNo(rs.getInt("memNo"));
				member.setMemId(rs.getString("memId"));
				member.setMemPwd(rs.getString("memPwd"));
				member.setMemName(rs.getString("memName"));
				member.setMemSname(rs.getString("memSname"));
				member.setMemGender(rs.getInt("memGender"));
				member.setMemIdNo(rs.getString("memIdNo"));
				member.setMemBday(rs.getDate("memBday"));
				member.setMemPhone(rs.getString("memPhone"));
				member.setMemAddress(rs.getString("memAddress"));
				member.setMemEmail(rs.getString("memEmail"));
				member.setMemImg(rs.getBytes("memImg"));
				member.setMemReported(rs.getInt("memReported"));
				member.setMemStatus(rs.getInt("memStatus"));
				member.setMemRelation(rs.getInt("memRelation"));
				member.setMemSelfintro(rs.getString("memSelfintro"));
				member.setMemFollowed(rs.getInt("memFollowed"));
				member.setMemPoint(rs.getInt("memPoint"));
				member.setMemSaleRank(rs.getInt("memSaleRank"));
				member.setMemLongtitude(rs.getDouble("memLongtitude"));
				member.setMemLatitude(rs.getDouble("memLatitude"));
				member.setMemLocTime(rs.getTimestamp("memLocTime"));
				member.setMemLocStatus(rs.getInt("memLocStatus"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return member;
	}

	@Override
	public List<Pet> findPetsByMemNo(Integer memno) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		List<Pet> list=new ArrayList<Pet>();
		
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement(FIND_PETS_BY_MEMNO);
			pstmt.setInt(1,memno);
			rs=pstmt.executeQuery();
			while(rs.next()){
			Pet pet=new Pet();
			pet.setPetNo(rs.getInt("petNo"));
			pet.setMemNo(rs.getInt("memNo"));
			pet.setPetName(rs.getString("petName"));
			pet.setPetKind(rs.getString("petKind"));
			pet.setPetGender(rs.getInt("petGender"));
			pet.setPetSpecies(rs.getString("petSpecies"));
			pet.setPetIntro(rs.getString("petIntro"));
			pet.setPetBday(rs.getDate("petBday"));
			pet.setPetImg(rs.getBytes("petImg"));
			pet.setPetStatus(rs.getInt("petStatus"));
			list.add(pet);
			}
		}
        catch (SQLException e) {
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
		
		return list;
	}

}

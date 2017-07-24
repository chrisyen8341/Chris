package com.pet.model;

import java.sql.Blob;
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

public class PetDAO implements PetDAO_interface {
	private static DataSource ds;
	private int currSeq;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO PET(PETNO, MEMNO,PETNAME,PETKIND,PETGENDER,PETSPECIES,PETINTRO,PETBDAY,PETIMG,PETSTATUS)"
			+ " VALUES(PETNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE PET SET PETNO = ?, MEMNO = ?, PETNAME = ?, "
			+ "PETKIND = ?, PETGENDER = ?, PETSPECIES = ?, PETINTRO = ?, PETBDAY = ?, PETIMG = ?, PETSTATUS = ? WHERE PETNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM PET WHERE PETNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PET WHERE PETNO = ?";
	private static final String GET_ALL = "SELECT * FROM PET";
	private static final String GET_CURRSEQ = "SELECT PETNO_SQ.CURRVAL FROM DUAL";

	@Override
	public void add(Pet pet) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, pet.getMemNo());
			pstmt.setString(2, pet.getPetName());
			pstmt.setString(3, pet.getPetKind());
			pstmt.setInt(4, pet.getPetGender());
			pstmt.setString(5,pet.getPetSpecies());
			pstmt.setString(6, pet.getPetIntro());
			pstmt.setDate(7, pet.getPetBday());
			Blob blob=con.createBlob();
			blob.setBytes(1, pet.getPetImg());
			pstmt.setBlob(8, blob);
			pstmt.setInt(9, pet.getPetStatus());
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
	public void add2(Pet pet, Connection con) {
		PreparedStatement pstmt=null;
		
		try {
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, pet.getMemNo());
			pstmt.setString(2, pet.getPetName());
			pstmt.setString(3, pet.getPetKind());
			pstmt.setInt(4, pet.getPetGender());
			pstmt.setString(5,pet.getPetSpecies());
			pstmt.setString(6, pet.getPetIntro());
			pstmt.setDate(7, pet.getPetBday());
			Blob blob=con.createBlob();
			blob.setBytes(1, pet.getPetImg());
			pstmt.setBlob(8, blob);
			pstmt.setInt(9, pet.getPetStatus());
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

		}
		
	}
	
	

	@Override
	public void update(Pet pet) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, pet.getPetNo());
			pstmt.setInt(2, pet.getMemNo());
			pstmt.setString(3, pet.getPetName());
			pstmt.setString(4, pet.getPetKind());
			pstmt.setInt(5, pet.getPetGender());
			pstmt.setString(6,pet.getPetSpecies());
			pstmt.setString(7, pet.getPetIntro());
			pstmt.setDate(8, pet.getPetBday());
			Blob blob=con.createBlob();
			blob.setBytes(1, pet.getPetImg());
			pstmt.setBlob(9, blob);
			pstmt.setInt(10, pet.getPetStatus());
			pstmt.setInt(11, pet.getPetNo());
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
	public void delete(int petNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, petNo);
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
	public Pet findByPk(int petNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Pet pet=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, petNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				pet=new Pet();
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
			}
			
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
		return pet;
	}

	@Override
	public List<Pet> getAll() {
		List<Pet> petList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL);
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
				petList.add(pet);		
			}
			
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
		return petList;
	}

	public int getCurrSeq() {
		return currSeq;
	}



}

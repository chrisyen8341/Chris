package com.pet.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class PetDAO implements PetDAO_interface{
	private static DataSource ds;
	private int currSeq;
	
static{
	try{
		Context ctx = new javax.naming.InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
	}
	catch(NamingException e){
		e.printStackTrace();
	}
}
	private static final String INSERT_STMT = "INSERT INTO PET(PETNO, MEMNO,PETNAME,PETKIND,PETGENDER,PETSPECIES,PETINTRO,PETBDAY,PETIMG)"
			+ " VALUES(PETNO_SQ.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE IMG SET IMGNO = ?, INAME = ?, IEXP = ?, "
			+ "ITIME = ?,ISIZE=? ,IMG = ?";
	private static final String DELETE_STMT = "DELETE FROM IMG WHERE IMGNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM IMG WHERE IMGNO = ?";
	private static final String GET_ALL = "SELECT * FROM IMG";
	private static final String GET_CURRSEQ = "SELECT PETNO_SQ.CURRVAL FROM DUAL";
	private static final String FIND_BY_ID = "SELECT MEM_PWD FROM MEMBER WHERE MEM_ID = ' ? '";
	@Override
	public void add(Pet pet) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, pet.getMemNo());
			pstmt.setString(2, pet.getPetName());
			pstmt.setString(3, pet.getPetKind());
			pstmt.setString(4, pet.getPetGender());
			pstmt.setString(5,pet.getPetSpecies());
			pstmt.setString(6, pet.getPetIntro());
			pstmt.setDate(7, pet.getPetBday());
			Blob blob=con.createBlob();
			blob.setBytes(1, pet.getPetImg());
			pstmt.setBlob(8, blob);
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
	public void update(Pet pet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int petNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pet findByPk(int petNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCurrSeq(){
		return currSeq;
	}

}

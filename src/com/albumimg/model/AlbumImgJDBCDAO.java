package com.albumimg.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pet.model.Pet;

public class AlbumImgJDBCDAO implements AlbumImgDAO_interface {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "TEST";
	private static final String PASSWORD = "c83758341";
	
	
	private static final String INSERT_STMT = "INSERT INTO ALBUMIMG(IMGNO, ALBUMNO,IMGTITLE,IMGDESC,IMGCREATEDTIME,IMGMODIFIEDTIME,IMGFILENAME,"
			+ "IMGEXTNAME,IMGFILE) VALUES(ALBUMIMG_SQ.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ALBUMIMG SET IMGNO = ?, ALBUMNO = ?, IMGTITLE = ?, "
			+ "IMGDES = ?, IMGCREATEDTIME = ?, IMGMODIFIEDTIME = ?, IMGFILENAME = ?, IMGEXTNAME = ?, IMGFILE = ? WHERE IMGNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM ALBUMIMG WHERE IMGNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ALBUMIMG WHERE IMGNO = ?";
	private static final String GET_ALL = "SELECT * FROM ALBUMIMG";
	

	
	@Override
	public void add(AlbumImg albumImg) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, albumImg.getAlbumNo());
			pstmt.setString(2, albumImg.getImgTitle());
			pstmt.setString(3, albumImg.getImgDesc());
			pstmt.setTimestamp(4, albumImg.getImgCreatedTime());
			pstmt.setTimestamp(5, albumImg.getImgModifiedTime());
			pstmt.setString(6, albumImg.getImgFileName());
			pstmt.setString(7, albumImg.getImgExtName());
			Blob blob=con.createBlob();
			blob.setBytes(1, albumImg.getImgFile());
			pstmt.setBlob(8, blob);
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
	public void update(AlbumImg albumImg) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, albumImg.getImgNo());
			pstmt.setInt(2, albumImg.getAlbumNo());
			pstmt.setString(3, albumImg.getImgTitle());
			pstmt.setString(4, albumImg.getImgDesc());
			pstmt.setTimestamp(5, albumImg.getImgCreatedTime());
			pstmt.setTimestamp(6, albumImg.getImgModifiedTime());
			pstmt.setString(7, albumImg.getImgFileName());
			pstmt.setString(8, albumImg.getImgExtName());
			Blob blob=con.createBlob();
			blob.setBytes(1, albumImg.getImgFile());
			pstmt.setBlob(9, blob);
			pstmt.setInt(10, albumImg.getImgNo());
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
	public void delete(int imgNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, imgNo);
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
	public AlbumImg findByPk(int imgNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		AlbumImg albumImg=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, imgNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				albumImg=new AlbumImg();
				albumImg.setImgNo(rs.getInt("imgNo"));
				albumImg.setAlbumNo(rs.getInt("albumNo"));
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
		return albumImg;
	}

	@Override
	public List<AlbumImg> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

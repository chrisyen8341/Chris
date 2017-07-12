package com.album.model;

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

public class AlbumDAO implements AlbumDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO ALBUM(ALBUMNO, MEMNO,ALBUMTITLE,ALBUMCREATEDTIME,AlbumModifiedTime,ALBUMSTATUS,ALBUMIMGFILE)"
			+ " VALUES(ALBUMNO_SQ.NEXTVAL,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ALBUM SET ALBUMNO = ?, MEMNO = ?, ALBUMTITLE = ?, "
			+ "ALBUMCREATEDTIME = ?, ALBUMMODIFIEDTIME = ?, ALBUMSTATUS = ?, ALBUMIMGFILE = ? WHERE ALBUMNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM ALBUM WHERE ALBUMNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ALBUM WHERE ALBUMNO = ?";
	private static final String GET_ALL = "SELECT * FROM ALBUM";
	private static final String GET_CURRSEQ = "SELECT ALBUMNO_SQ.CURRVAL FROM DUAL";

	@Override
	public void add(Album album) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, album.getMemNo());
			pstmt.setString(2, album.getAlbumTitle());
			pstmt.setTimestamp(3, album.getAlbumCreatedTime());
			pstmt.setTimestamp(4, album.getAlbumModifiedTime());
			pstmt.setInt(5,album.getAlbumStatus());
			Blob blob=con.createBlob();
			blob.setBytes(1, album.getAlbumImgFile());
			pstmt.setBlob(6, blob);
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
	public void update(Album album) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, album.getAlbumNo());
			pstmt.setInt(2, album.getMemNo());
			pstmt.setString(3, album.getAlbumTitle());
			pstmt.setTimestamp(4, album.getAlbumCreatedTime());
			pstmt.setTimestamp(5, album.getAlbumModifiedTime());
			pstmt.setInt(6,album.getAlbumStatus());
			Blob blob=con.createBlob();
			blob.setBytes(1, album.getAlbumImgFile());
			pstmt.setBlob(7, blob);
			pstmt.setInt(8, album.getAlbumNo());
			
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
	public void delete(int albumNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, albumNo);
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
	public Album findByPk(int albumNo) {
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		Album album=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, albumNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				album=new Album();
				album.setAlbumNo(rs.getInt("albumNo"));
				album.setMemNo(rs.getInt("memNo"));
				album.setAlbumTitle(rs.getString("albumTitle"));
				album.setAlbumCreatedTime(rs.getTimestamp("albumCreatedTime"));
				album.setAlbumModifiedTime(rs.getTimestamp("albumModifiedTime"));
				album.setAlbumStatus(rs.getInt("albumStatus"));
				album.setAlbumImgFile(rs.getBytes("albumImgFile"));
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
		return album;
	}

	@Override
	public List<Album> getAll() {
		List<Album> albumList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Album album=new Album();
				album.setAlbumNo(rs.getInt("albumNo"));
				album.setMemNo(rs.getInt("memNo"));
				album.setAlbumTitle(rs.getString("albumTitle"));
				album.setAlbumCreatedTime(rs.getTimestamp("albumCreatedTime"));
				album.setAlbumModifiedTime(rs.getTimestamp("albumModifiedTime"));
				album.setAlbumStatus(rs.getInt("albumStatus"));
				album.setAlbumImgFile(rs.getBytes("albumImgFile"));
				albumList.add(album);		
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
		return albumList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

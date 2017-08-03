package com.empauth.model;

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

public class EmpAuthDAO implements EmpAuthDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO EMPAUTH(EMPNO,AUTHNO)" + " VALUES(?,?)";
	private static final String UPDATE_STMT = "UPDATE EMPAUTH SET EMPNO = ?, AUTHNO = ? WHERE EMPNO = ? AND AUTHNO =¡@?";
	private static final String DELETE_STMT = "DELETE FROM EMPAUTH WHERE EMPNO = ? AND AUTHNO =¡@?";
	private static final String DELETE2_STMT = "DELETE FROM EMPAUTH WHERE EMPNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM EMPAUTH WHERE EMPNO = ? AND AUTHNO =¡@?";
	private static final String FIND_BY_EMPNO = "SELECT * FROM EMPAUTH WHERE EMPNO = ?";
	private static final String GET_ALL = "SELECT * FROM EMPAUTH";

	@Override
	public void add(EmpAuth empAuth) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, empAuth.getEmpNo());
			pstmt.setInt(2, empAuth.getAuthNo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void add2(EmpAuth empAuth, Connection con) {
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, empAuth.getEmpNo());
			pstmt.setInt(2, empAuth.getAuthNo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	public void update(EmpAuth empAuth) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, empAuth.getEmpNo());
			pstmt.setInt(2, empAuth.getAuthNo());
			pstmt.setInt(3, empAuth.getEmpNo());
			pstmt.setInt(4, empAuth.getAuthNo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(int empNo, int authNo) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, empNo);
			pstmt.setInt(2, authNo);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public EmpAuth findByPk(int empNo, int authNo) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		EmpAuth empAuth = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, empNo);
			pstmt.setInt(2, authNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empAuth = new EmpAuth();
				empAuth.setEmpNO(rs.getInt("empNo"));
				empAuth.setAuthNo(rs.getInt("authNo"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return empAuth;
	}

	@Override
	public List<EmpAuth> getAll() {
		List<EmpAuth> empAuthList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpAuth empAuth = new EmpAuth();
				empAuth.setEmpNO(rs.getInt("empNo"));
				empAuth.setAuthNo(rs.getInt("authNo"));
				empAuthList.add(empAuth);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return empAuthList;
	}

	@Override
	public List<EmpAuth> findByEmpNo(int empNo) {
		List<EmpAuth> empAuthList = new ArrayList<>();
		PreparedStatement pstmt=null;
		Connection con=null;
		ResultSet rs=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_EMPNO);
			pstmt.setInt(1, empNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				EmpAuth empAuth=new EmpAuth();
				empAuth.setEmpNO(rs.getInt("empNo"));
				empAuth.setAuthNo(rs.getInt("authNo"));
				empAuthList.add(empAuth);		
			}
			
		}  catch (SQLException e) {
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
		return empAuthList;
	}

	@Override
	public void delete2(int empNo) {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE2_STMT);
			pstmt.setInt(1, empNo);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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



}

package customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerFunction {

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null && !rs.isClosed()) { // close할 때 생길 문제 예방
				rs.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// custId값으로 한명의 고객을 조회
	public CustomerVO selectCustomer(int varCustId) {
		// 실행쿼리 작성
		String sql = "SELECT * FROM CUSTOMER "
				   + "WHERE CUSTID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		CustomerVO cvo = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, varCustId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cvo = new CustomerVO();
				int custId = rs.getInt("CUSTID");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				String phone = rs.getString("PHONE");

				cvo.setCustId(custId);
				cvo.setName(name);
				cvo.setAddress(address);
				cvo.setPhone(phone);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return cvo;
	}

	public CustomerVO selectCustomer(String varName) {
		// 실행쿼리 작성
		String sql = "SELECT * FROM CUSTOMER "
				   + "WHERE NAME = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		CustomerVO cvo = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, varName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cvo = new CustomerVO();
				int custId = rs.getInt("CUSTID");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				String phone = rs.getString("PHONE");

				cvo.setCustId(custId);
				cvo.setName(name);
				cvo.setAddress(address);
				cvo.setPhone(phone);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return cvo;
	}
	
	public ArrayList<CustomerVO> selectCustomer(int item, String search) {
		// 실행쿼리 작성
		String sql = "SELECT * FROM CUSTOMER WHERE 1=1";
		if(item == 1) {			//custId
			sql += "AND CUSTID = ?";
		} else if (item==2) {	//name
			sql += "AND NAME = ?";
		} else if (item == 3) {	//address
			sql += "AND ADDRESS LIKE ?";
		} else if (item ==4) {	//phone
			sql += "AND PHONE LIKE ?";
		}
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<CustomerVO> vlist = new ArrayList<CustomerVO>();
		CustomerVO cvo = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			if(item == 1) {
				pstmt.setInt(1, Integer.parseInt(search));
			}else {
				pstmt.setString(1, search);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cvo = new CustomerVO();
				int custId = rs.getInt("CUSTID");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				String phone = rs.getString("PHONE");
				cvo.setCustId(custId);
				cvo.setName(name);
				cvo.setAddress(address);
				cvo.setPhone(phone);
				vlist.add(cvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return vlist;
	}
	
	// 전체 고객 조회 (Customer 체이블 전체조회)
	public ArrayList<CustomerVO> selectAllCustomer() {
		// 실행쿼리 작성
		String sql = "SELECT * FROM CUSTOMER ORDER BY CUSTID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<CustomerVO> clist = null;
		CustomerVO cvo = null;
		
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			clist = new ArrayList<CustomerVO>();
			while (rs.next()) {
				cvo = new CustomerVO();
				cvo.setCustId(rs.getInt("CUSTID"));
				cvo.setName(rs.getString("NAME"));
				cvo.setAddress(rs.getString("ADDRESS"));
				cvo.setPhone(rs.getString("PHONE"));
				clist.add(cvo);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return clist;
		
	}

	// insert
	public int insertCustomer(int custId, String name, String address, String phone) {
		String sql = "INSERT INTO CUSTOMER(CUSTID, NAME, ADDRESS, PHONE) " + "VALUES (?,?,?,?)";
		int result = 0;

		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, custId);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, phone);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}

	// update
	public int updateCustomer(int updateitem, int custId, String name, String address, String phone) {
		String sql = "UPDATE CUSTOMER SET" ;
		if(name != null) {
			sql += " NAME = ?," ;
			
		} if(address != null) {
			sql +=  " ADDRESS = ?,";
			
		} if(phone != null) {
			sql += " PHONE = ?,";
			
		}
		sql= sql.substring(0, sql.length()-1);
		sql += " WHERE CUSTID = ?";
		int result = 0;

		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			if(name != null && address == null && phone == null) {			//1
				pstmt.setString(1, name);
				pstmt.setInt(2, custId);
				
			} else if(name == null && address != null && phone == null) {	//2
				pstmt.setString(1, address);
				pstmt.setInt(2, custId);
				
			} else if(name == null && address == null && phone != null) {	//3
				pstmt.setString(1, phone);
				pstmt.setInt(2, custId);
			} else {														//4
				pstmt.setString(1, name);
				pstmt.setString(2, address);
				pstmt.setString(3, phone);
				pstmt.setInt(4, custId);
				
			}

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}

	public int updateCustomer(int custId, String name, String address, String phone) {
		String sql = "UPDATE CUSTOMER "
					+ "SET NAME = ?, "
					+ "	   ADDRESS = ?, "
					+ "    PHONE = ? "
					+ "WHERE CUSTID = ?";
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, phone);
			pstmt.setInt(4, custId);
			result = pstmt.executeUpdate();
			System.out.println(result + "개 값이 수정 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	// delete
	public int deleteCustomer(int custId) {
		String sql = "DELETE FROM CUSTOMER " 
				   + "WHERE CUSTID = ?";
		int result = 0;

		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	public int deleteCustomer(String name) {
		String sql = "DELETE FROM CUSTOMER " 
				+ "WHERE NAME = ?";
		int result = 0;
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
}

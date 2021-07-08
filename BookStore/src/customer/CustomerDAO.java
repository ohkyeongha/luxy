package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbcon.DBconnect;

public class CustomerDAO {

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int idCheck(String custId) {
		String sql = "SELECT count(*) FROM CUSTOMER WHERE CUSTID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		try {
			conn = DBconnect.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custId);

			rs = pstmt.executeQuery();
			while (rs.next()) { // 하나의 레코드(행)을 지시함
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public int login(CustomerVO cvo) {
		Connection conn = null;
		PreparedStatement pstm = null;
//		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {

			conn = DBconnect.getInstance();
			String quary = "SELECT COUNT(*) FROM CUSTOMER WHERE CUSTID = ? and PWD =?";
			pstm = conn.prepareStatement(quary);
			pstm.setString(1, cvo.getCustId());
			pstm.setString(2, cvo.getPwd());

			rs = pstm.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstm != null && !pstm.isClosed()) {
					pstm.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertCustomer(CustomerVO cvo) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int result = 0;
		try {
			conn = DBconnect.getInstance();
			String quary = "INSERT into customer (custid, pwd, name , address, phone)" 
						+ " values (?,?,?,?,?)";
//			stmt = conn.createStatement();
//			rs = stmt.executeUpdate(sql)
			pstm = conn.prepareStatement(quary);
			pstm.setString(1, cvo.getCustId());
			pstm.setString(2, cvo.getPwd());
			pstm.setString(3, cvo.getName());
			pstm.setString(4, cvo.getAddress());
			pstm.setString(5, cvo.getPhone());
			result = pstm.executeUpdate();

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} finally {

			try {
				if (pstm != null && !pstm.isClosed()) {
					pstm.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}

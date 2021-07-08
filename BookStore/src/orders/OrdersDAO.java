package orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbcon.DBconnect;

public class OrdersDAO {
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
	
	//전체 select
	public ArrayList<OrdersVO> selectOrder(String custId){
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "SELECT O.ORDERID, B.BOOKNAME, O.SALEPRICE, O.COUNT, O.ORDERDATE "
				+ "FROM ORDERS O , BOOK B "
				+ "WHERE O.BOOKID = B.BOOKID "
				+ "AND O.CUSTID = ? "
				+ "ORDER BY O.ORDERID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrdersVO> olist = new ArrayList<OrdersVO>();
		//preparedstatement 객체 생성
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custId);
			// ? 인자값 넣어주기
			//Resultset 결과값 담기
			rs = pstmt.executeQuery();
			//List에 결과값 담기
			OrdersVO ovo = null;
			while(rs.next()) {
				ovo = new OrdersVO();
				ovo.setOrderId(rs.getInt(1));
				ovo.setBookName(rs.getString(2));
				ovo.setSalePrice(rs.getInt(3));
				ovo.setCount(rs.getInt(4));
				ovo.setOrderDate(rs.getDate(5));
				olist.add(ovo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return olist;
	}
	
	
	//insert
	public int insertOrders(OrdersVO ovo) {
		Connection conn = DBconnect.getInstance();
		String sql = "INSERT INTO ORDERS (ORDERID, CUSTID, BOOKID, SALEPRICE, ORDERDATE, COUNT) "
				+ "VALUES (ORDERS_ORDERID_SEQ.NEXTVAL,?,?,?,SYSDATE,?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ovo.getCustId());
			pstmt.setInt(2, ovo.getBookId());
			pstmt.setInt(3, ovo.getSalePrice());
			pstmt.setInt(4, ovo.getCount());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	//update (bookid)
	public int updateBook(OrdersVO ovo) {
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "UPDATE BOOK "
				+ "SET BOOKNAME = ?, "
				+"     PUBLISHER = ?, "
				+ "    PRICE = ? "
				+ "WHERE BOOKID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
//			pstmt.setString(1, bvo.getBookName());
//			pstmt.setString(2, bvo.getPublisher());
//			pstmt.setInt(3, bvo.getPrice());
//			pstmt.setInt(4, bvo.getBookId());
			
			//Resultset 결과값 담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	//delete
	public int deleteOrders(int orderId) {
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "DELETE FROM ORDERS "
				+ "WHERE ORDERID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
			pstmt.setInt(1, orderId);
			
			//Resultset 결과값 담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

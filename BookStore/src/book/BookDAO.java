package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbcon.DBconnect;

public class BookDAO {
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
	public ArrayList<BookVO> selectAllBook(){
		//DB connection 연결
		Connection conn = DBconnect.getInstance(); 
		//실행쿼리
		String sql = "SELECT * FROM BOOK ORDER BY BOOKID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookVO> blist = new ArrayList<BookVO>();
		//preparedstatement 객체 생성
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
			//Resultset 결과값 담기
			rs = pstmt.executeQuery();
			//List에 결과값 담기
			BookVO bvo = null;
			while(rs.next()) {
				bvo = new BookVO();
				bvo.setBookId(rs.getInt("BOOKID"));
				bvo.setBookName(rs.getString("BOOKNAME"));
				bvo.setPublisher(rs.getString("PUBLISHER"));
				bvo.setPrice(rs.getInt("PRICE"));
				bvo.setImgPath(rs.getString("IMGPATH"));
				blist.add(bvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return blist;
	}
	
	
	//하나 select (bookid)
	public BookVO selectBook(int bookId) {
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "SELECT * FROM BOOK WHERE BOOKID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO bvo = null;
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
			pstmt.setInt(1, bookId);
			//Resultset 결과값 담기
			rs = pstmt.executeQuery();
			//List에 결과값 담기
			while(rs.next()) {
				bvo = new BookVO();
				bvo.setBookId(rs.getInt("BOOKID"));
				bvo.setBookName(rs.getString("BOOKNAME"));
				bvo.setPublisher(rs.getString("PUBLISHER"));
				bvo.setPrice(rs.getInt("PRICE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
			
		}
		return bvo;
	}
	
	public int selectBookSeq() {
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "SELECT MAX(BOOKID) FROM BOOK";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//Resultset 결과값 담기
			rs = pstmt.executeQuery();
			//List에 결과값 담기
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
			
		}
		return result;
	}
	
	
	
	
	public ArrayList<BookVO> selectBook(String bookName) {
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "SELECT * FROM BOOK WHERE BOOKNAME LIKE ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO bvo = null;
		ArrayList<BookVO> blist = new ArrayList<BookVO>();
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
			pstmt.setString(1, "%"+bookName+"%");
			//Resultset 결과값 담기
			rs = pstmt.executeQuery();
			//List에 결과값 담기
			while(rs.next()) {
				bvo = new BookVO();
				bvo.setBookId(rs.getInt("BOOKID"));
				bvo.setBookName(rs.getString("BOOKNAME"));
				bvo.setPublisher(rs.getString("PUBLISHER"));
				bvo.setPrice(rs.getInt("PRICE"));
				blist.add(bvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
			
		}
		return blist;
	}
	
	public ArrayList<BookVO> selectBook(String item, String search) {
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "SELECT * FROM BOOK "
				+ "WHERE 1=1 ";
		if(item.equals("책번호"))	{
			sql += "AND BOOKID = ?";
		}else if(item.equals("책이름")) {
			sql += "AND BOOKNAME LIKE ?";
		}else if(item.equals("출판사")) {
			sql += "AND PUBLISHER LIKE ?";
		}else {
			sql += "AND PRICE LIKE ?";
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO bvo = null;
		ArrayList<BookVO> blist = new ArrayList<BookVO>();
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
			if(item.equals("책번호")) {
				pstmt.setInt(1, Integer.parseInt(search));
			}else {
				pstmt.setString(1, "%"+search+"%");
			}
			
			
			//Resultset 결과값 담기
			rs = pstmt.executeQuery();
			//List에 결과값 담기
			while(rs.next()) {
				bvo = new BookVO();
				bvo.setBookId(rs.getInt("BOOKID"));
				bvo.setBookName(rs.getString("BOOKNAME"));
				bvo.setPublisher(rs.getString("PUBLISHER"));
				bvo.setPrice(rs.getInt("PRICE"));
				blist.add(bvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
			
		}
		return blist;
	}
	
	
	//insert
	public int insertBook(BookVO bvo) {
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "INSERT INTO BOOK (BOOKID, BOOKNAME, PUBLISHER, PRICE) "
				+ "VALUES (book_bookid_seq.nextval,?,?,?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
			pstmt.setString(1, bvo.getBookName());
			pstmt.setString(2, bvo.getPublisher());
			pstmt.setInt(3, bvo.getPrice());
			
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
	
	//update (bookid)
	public int updateBook(BookVO bvo) {
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
			pstmt.setString(1, bvo.getBookName());
			pstmt.setString(2, bvo.getPublisher());
			pstmt.setInt(3, bvo.getPrice());
			pstmt.setInt(4, bvo.getBookId());
			
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
	
	//delete (bookid)
	public int deleteBook(int bookId) {
		//DB connection 연결
		Connection conn = DBconnect.getInstance();
		//실행쿼리
		String sql = "DELETE FROM BOOK "
				+ "WHERE BOOKID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
			pstmt.setInt(1, bookId);
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

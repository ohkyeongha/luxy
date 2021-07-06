package jdbctest;

import java.sql.*;
import java.util.Scanner;

public class JdbcEx1 {

	public static void main(String[] args) {
		// 계정
		String user = "madang";
		// 비밀번호
		String password = "madang";
		// URL - ip, port, sid
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		// connection - 드라이버로딩 (ojdbc.jar 파일을 활용하여 DB연결)
		Connection conn = null;
		try {
			// DB연결(연결완료)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB 연결 완료");

		} catch (ClassNotFoundException e) { // Class.forName에서 에러가 났을 때
			System.out.println("ojdbc.jar이 없습니다.(드라이버가 존재하지 않습니다.");
			e.printStackTrace();
		} catch (SQLException e) { // DirverManager에서 에러가 났을 때
			System.out.println("url,user,passwor 및 DB가 켜져있는지 확인하세요.");
			e.printStackTrace();
		}

		System.out.print("책이름 조회 : ");
		Scanner sc = new Scanner(System.in);

		// 실행쿼리 작성
		String bookNameVar = sc.nextLine();
//		String sql = "SELECT * FROM BOOK WHERE BOOKNAME LIKE '%" + bookNameVar + "%'";
		String sql = "SELECT * FROM BOOK WHERE BOOKNAME LIKE ?";
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// DB쿼리 실행을 위해 statement, preparendstatement를 생성
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + bookNameVar + "%");

			// 쿼리 실행한 결과를 Resultset에 담아오기
//			rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();

			// Resultset에 담은 결과를 while문을 통해 하나씩 꺼내오기
			while (rs.next()) {
				int bookId = rs.getInt("BOOKID"); // int bookID = rs.getInt(1);
				String bookName = rs.getString("BOOKNAME");
				String publisher = rs.getString("PUBLISHER");
				int price = rs.getInt("PRICE");

				System.out.println(bookId + ", " + bookName + ", " + publisher + ", " + price);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) { // close할 때 생길 문제 예방
					rs.close();
				}
//				if(stmt != null && !stmt.isClosed()) {	
//					stmt.close();
//				}
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

	}

}

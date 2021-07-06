package jdbctest;

import java.sql.*;
import java.util.Scanner;

public class JdbcEx7 {

	public static void main(String[] args) {
		String user = "madang";
		String password = "madang";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB 연결 완료");

		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc.jar이 없습니다.(드라이버가 존재하지 않습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("url,user,passwor 및 DB가 켜져있는지 확인하세요.");
			e.printStackTrace();
		}

		// 스캐너로 값을 넣기.
		Scanner sc = new Scanner(System.in);

		System.out.print("수정할 고객 번호:\t");
		int custId = sc.nextInt();
		sc.nextLine();
		System.out.print("고객 이름:\t");
		String name = sc.nextLine();

		System.out.print("주소:\t");
		String address = sc.nextLine();

//		System.out.print("전화번호:\t");
//		String phone = sc.nextLine();

		// 실행쿼리
		String sql = "UPDATE CUSTOMER " + "SET NAME = ?, " + "		ADDRESS = ? " + "WHERE CUSTID = ?";

		int result = 0; // 결과값을 반환해주기위한 변수

		PreparedStatement pstmt = null;
		try {
			// db쿼리 실행을 위한 preparedStatemen 객체 생성
			pstmt = conn.prepareStatement(sql);

			// ?에 값 넣어주기
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setInt(3, custId);

			// 쿼리 실행 및 결과값 저장
			result = pstmt.executeUpdate(); // 0과1로 반환

			System.out.println(result + "개의 값이 수정되었습니다");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				sc.close();
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

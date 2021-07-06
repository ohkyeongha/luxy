package customer;

import java.sql.*;

public class DBConnect {
	
	//DB 연결 객체
	public static Connection getConnection() {
		
		String user = "madang";// 계정
		String password = "madang";// 비밀번호
		String url = "jdbc:oracle:thin:@localhost:1521:xe";// URL - ip, port, sid
		
		// connection - 드라이버로딩 (ojdbc.jar 파일을 활용하여 DB연결)
		Connection conn = null;
		try {
			// DB연결
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
		
		return conn;
	}
}

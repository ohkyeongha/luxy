package com.kitri.miniboard.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kitri.miniboard.db.DBConnect;
import com.sun.jdi.request.ClassPrepareRequest;


public class MemberDAO {
	//로그인(id, pw) 조회
	public String selectLogin(String userId, String userPw) {
		//db connection
		Connection conn = DBConnect.getInstance();
		
		//sql
		String sql = "SELECT USERID FROM MEMBER WHERE USERID=? AND USERPW=?";
		String result = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//prepared - 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			//resultset - 결과값
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//가입(id, pw, email) 등록
	public int insertSignup(MemberVO mvo) {
		//conn
		Connection conn = DBConnect.getInstance();
		
		//sql
		String sql = "INSERT INTO MEMBER (USERID, USERPW, EMAIL)"
				+ " VALUES (?,?,?)";
		int result=0;
		
		PreparedStatement pstmt = null;
		
		try {
			//prepared
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getUserId());
			pstmt.setString(2, mvo.getUserPw());
			pstmt.setString(3, mvo.getEmail());
			
			//result
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}
		return result;
	}
	
	//id 조회
	public String selectUserId(String userId) {
		//conn
		Connection conn = DBConnect.getInstance();
		//sql
		String sql = "SELECT USERID FROM MEMVER WHERE USERID = ?";
		String result = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//prepared
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			//resultset
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//result
				result = rs.getString("USERID");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return result;
		
	}
	
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
			if(pstmt!=null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}


















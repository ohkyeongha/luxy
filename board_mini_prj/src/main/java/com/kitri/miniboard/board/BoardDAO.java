package com.kitri.miniboard.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.miniboard.board.*;
import com.kitri.miniboard.db.DBConnect;

public class BoardDAO {
	
	public void closeAll(Connection conn, PreparedStatement pstmt, Statement stmt, ResultSet rs) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
			if(pstmt!=null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();
			}
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//게시판 전체 글 조회
	public List<BoardVO> selectBoardList(){
		//conn
		Connection conn = DBConnect.getInstance();
		//sql
		String sql = "SELECT * FROM BOARD ORDER BY BNO DESC";
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardVO> blist = new ArrayList<BoardVO>();
		//statement
		try {
			stmt = conn.createStatement();
			//resultset
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBno(rs.getInt("bno"));
				bvo.setbTitle(rs.getString(2));
				bvo.setbContent(rs.getString(3));
				bvo.setbWriter(rs.getString(4));
				bvo.setbRegDate(rs.getDate(5));
				
				//list<boardDAO>
				blist.add(bvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		
		return blist;
	}
	
	public int insertBoard(BoardVO bvo) {
	      //conn
	      Connection conn = DBConnect.getInstance();
	      
	      //sql
	      String sql="INSERT INTO BOARD (BNO, BTITLE, BCONTENT, BWRITER, BREGDATE)"
	            + " VALUES (BOARD_BNO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
	      
	      //prepared
	      PreparedStatement pstmt = null;
	      int result = 0;
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, bvo.getbTitle());
	         pstmt.setString(2, bvo.getbContent());
	         pstmt.setString(3, bvo.getbWriter());
	         
	         //result
	         result = pstmt.executeUpdate();
	         
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         closeAll(conn, pstmt, null, null);
	      }
	      return result;
	}
	public BoardVO selectBoard(int bno) {
		
		//conn
		Connection conn = DBConnect.getInstance();
		//sql
		String sql = "SELECT * FROM BOARD WHERE BNO = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO bvo = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			//resultset
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bvo = new BoardVO();
				bvo.setBno(rs.getInt("BNO"));
				bvo.setbTitle(rs.getString("BTITLE"));
				bvo.setbContent(rs.getString("BCONTENT"));
				bvo.setbWriter(rs.getString("BWRITER"));
				bvo.setbRegDate(rs.getDate("BREGDATE"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, rs);
		}
		
		return bvo;
	}
	
	public int updateBoard(BoardVO bvo) {
		Connection conn = DBConnect.getInstance();
		String sql = "UPDATE BOARD "
				+ "SET BTITLE = ?,"
				+ "	   BCONTENT = ?"
				+ " WHERE BNO = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getbTitle());
			pstmt.setString(2, bvo.getbContent());
			pstmt.setInt(3, bvo.getBno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
	}
	
	public int deleteBoard(int bno) {
		Connection conn = DBConnect.getInstance();
		String sql = "DELETE FROM BOARD "
				+ " WHERE BNO = ?";
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
	}
	
	public List<BoardVO> selectBoardPage(int start, int end){
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT * "
					+ "FROM (SELECT ROWNUM AS RNUM, A.* "
							+ "FROM (SELECT * "
							+ "FROM BOARD "
							+ "ORDER BY BNO DESC) A) "
					+ "WHERE RNUM BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> blist = new ArrayList<BoardVO>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBno(rs.getInt("BNO"));	
				bvo.setbTitle(rs.getString("BTITLE"));
				bvo.setbContent(rs.getString("BCONTENT"));
				bvo.setbWriter(rs.getString("BWRITER"));
				bvo.setbRegDate(rs.getDate("BREGDATE"));
				
				blist.add(bvo);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, rs);
		}
		
		return blist;
	}
	
	public int selectBoardCnt() {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT COUNT(*) FROM BOARD";
		
		Statement stmt = null;
		ResultSet rs = null;
		int result=0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		
		return result;
	}
	
}


















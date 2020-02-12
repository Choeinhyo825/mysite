package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageInfo;

public class BoardRepository {
	
	public long searchBoardListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		long listCount = 0;
		
		try {
			conn = getConnection();
			String sql = "SELECT COUNT(*) FROM board";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				listCount = rset.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listCount;
		
	}
	
	public List<BoardVo> searchBoardRecord(PageInfo pi) {
		
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			conn = getConnection();
			String sql = "SELECT tb.RNUM , tb.no, tb.user_no, tb.title, tb.hit, tb.reg_date, tb.gno, tb.ono, tb.depth, tb.name FROM (SELECT @ROWNUM:=@ROWNUM+1 as RNUM, bb.no, bb.user_no, bb.title, bb.hit, bb.reg_date, bb.gno, bb.ono, bb.depth, bb.name FROM (select b.no, b.user_no, b.title, b.hit, b.reg_date, b.gno, b.ono, b.depth, u.name from board b join user u on(b.user_no = u.no) order by b.gno desc, b.ono asc) bb, (SELECT @ROWNUM:=0) TMP ) tb WHERE RNUM BETWEEN ? AND ? order by tb.gno desc, tb.ono";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pi.getStartPage());
			pstmt.setLong(2, pi.getEndpage());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				long rnum = rset.getLong(1);
				long no = rset.getLong(2);
				long userNo = rset.getLong(3);
				String title = rset.getString(4);
				long hit = rset.getLong(5);
				String regDate = rset.getString(6);
				long gno = rset.getLong(7);
				long ono = rset.getLong(8);
				long depth = rset.getLong(9);
				String name = rset.getString(10);

				BoardVo vo = new BoardVo();
				vo.setRnum(rnum);
				vo.setNo(no);
				vo.setUserNo(userNo);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGno(gno);
				vo.setOno(ono);
				vo.setDepth(depth);
				vo.setName(name);

				list.add(vo);

			}

		} catch (SQLException e) {
			System.err.println("에러 발생 : " + e);
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}

//	public long findAllCount() {		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		long listCount = 0;
//		
//		try {
//			conn = getConnection();
//			String sql = "SELECT COUNT(*) FROM board";
//			pstmt = conn.prepareStatement(sql);
//			rset = pstmt.executeQuery();
//
//			while(rset.next()) {
//				listCount = rset.getLong(1);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rset != null)
//					rset.close();
//				if (pstmt != null)
//					pstmt.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return listCount;
//	}

	public BoardVo selectContent(long num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BoardVo vo = new BoardVo();
		try {
			conn = getConnection();
			String sql = "select no, title, contents, user_no, gno, ono, depth from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, num);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				long no = rset.getLong(1);
				String title = rset.getString(2);
				String contents = rset.getString(3);
				long userNo = rset.getLong(4);
				long gno = rset.getLong(5);
				long ono = rset.getLong(6);
				long depth = rset.getLong(7);

				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setUserNo(userNo);
				vo.setGno(gno);
				vo.setOno(ono);
				vo.setDepth(depth);
			}

		} catch (SQLException e) {
			System.err.println("에러 발생 : " + e);
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	public Boolean hit(long no) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set hit = (select * from (select b.hit+1 from board b where b.no=?)as a) where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setLong(2, no);

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.err.println("에러 발생 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean write(BoardVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

				String sql = "insert into board values(null, ?, ?, ?, 0, now(), ifnull((select max(gno)+1 from board b),1),1,0)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setLong(1, vo.getUserNo());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getContents());

				int count = pstmt.executeUpdate();
				result = count == 1;
				
		} catch (SQLException e) {
			System.err.println("에러 발생 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public Boolean updateList(BoardVo vo) {
		
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set ono = ono +1 where gno = ? and ono > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getGno());
			pstmt.setLong(2, vo.getOno());

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.err.println("에러 발생 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean insertReply(BoardVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

				String sql = "insert into board values(null, ?, ?, ?, 0, now(), ?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setLong(1, vo.getUserNo());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getContents());
				pstmt.setLong(4, vo.getGno());
				pstmt.setLong(5, vo.getOno()+1);
				pstmt.setLong(6, vo.getDepth()+1);
				

				int count = pstmt.executeUpdate();
				result = count == 1;
				
		} catch (SQLException e) {
			System.err.println("에러 발생 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean modify(BoardVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set title = ?, contents = ? where no = ? and user_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			pstmt.setLong(4, vo.getUserNo());

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.err.println("에러 발생 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean delete(long no) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.err.println("에러 발생 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로딩 실패 : " + e);
		}
		return conn;
	}

}

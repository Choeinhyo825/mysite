package com.douzone.mysite.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.GuestbookVo;

public class GuestbookRepository {
	public Boolean insert(GuestbookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into guestbook values(null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getPass());

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
	
	public Boolean delete(GuestbookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from guestbook where no=? and pass=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPass());

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

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			conn = getConnection();
			String sql = "select no, name, contents, reg_date from guestbook order by 1 desc";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Long no = rset.getLong(1);
				String name = rset.getString(2);
				String contents = rset.getString(3);
				String regDate = rset.getString(4);
				
				GuestbookVo vo = new GuestbookVo();

				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);
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

	public Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.1.101:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로딩 실패 : " + e);
		}
		return conn;
	}
}

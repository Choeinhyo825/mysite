package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;

public class UserRepository {

	public int insert(UserVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into user values(null, ?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPass());
			pstmt.setString(4, vo.getGender());

			count = pstmt.executeUpdate();

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
		return count;
	}

	public UserVo login(UserVo vo) {

		UserVo userVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			conn = getConnection();
			String sql = "select no, name from user where email = ? and pass = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPass());
			rset = pstmt.executeQuery();

			if (rset.next()) {
				Long no = rset.getLong(1);
				String name = rset.getString(2);

				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
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
		return userVo;
	}

	public UserVo getFindByNo(Long no) {
		UserVo userVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			conn = getConnection();
			String sql = "select name, email, gender from user where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				String name = rset.getString(1);
				String email = rset.getString(2);
				String gender = rset.getString(3);

				userVo = new UserVo();
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setGender(gender);
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
		return userVo;
	}

	public int update(UserVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			if ("".equals(vo.getPass())) {
				String sql = "update user set name = ?, gender = ? where email = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setString(3, vo.getEmail());

			} else {
				String sql = "update user set name = ?,pass = ?, gender = ? where email = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPass());
				pstmt.setString(3, vo.getGender());
				pstmt.setString(4, vo.getEmail());
			}

			count = pstmt.executeUpdate();

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
		return count;
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

package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;

	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}

	public UserVo find(UserVo vo) {
		UserVo userVo = sqlSession.selectOne("user.findUser", vo);
		return userVo;
	}

	public UserVo find(Long no) {
		UserVo userVo = sqlSession.selectOne("user.findUserInfomation", no);
		return userVo;
	}

	public int updateUser(UserVo vo) {
		return sqlSession.insert("user.updateUser", vo);
	}
	
	public UserVo find(String email) {
		return sqlSession.selectOne("user.findByEmail", email);
	}

}

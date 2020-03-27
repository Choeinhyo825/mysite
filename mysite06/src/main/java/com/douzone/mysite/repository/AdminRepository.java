package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class AdminRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// 관리자 정보 가져오기
	public SiteVo selectProfile() {
		return sqlSession.selectOne("admin.selectProfile");
	}
	
	// 관리자 프로필 업데이트
	public int profileUpdate(SiteVo vo) {
		return sqlSession.update("admin.profileUpdate",vo);
	}


}

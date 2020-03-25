package com.douzone.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageInfo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;

	// 총 게시글 count
	public Long searchBoardListCount(String keyword) {
		return sqlSession.selectOne("board.searchBoardListCount", keyword);
	}

	// 페이징
	public List<BoardVo> searchBoardRecord(PageInfo pi) {
		return sqlSession.selectList("board.searchBoardRecord", pi);
	}

	// 답글시 업데이트
	public int updateOrderNo(BoardVo vo) {
		return sqlSession.update("board.updateOrederNo", vo);
	}

	// 게시글 작성
	public Long insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
		return vo.getNo();
	}

	// 글 상세보기
	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	// 조회수 증가
	public int updateHit(Long no) {
		return sqlSession.update("board.updateHit", no);
	}

	// 하위글 체크
	public Long findBoardGroup(Long no) {
		return sqlSession.selectOne("board.searchGroup", no);
	}

	// 글 삭제
	public int findByNoAndUserNo(Map<String, Long> map) {
		return sqlSession.delete("board.deletePost", map);
	}

	// 글 업데이트
	public int findByNoAndUserNoUpdate(Map<String, Long> map) {
		return sqlSession.delete("board.updatePost", map);
	}

	// 글 수정
	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);
	}
}

package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageInfo;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;

	public PageInfo getCount(PageInfo pi) {
		Long currentPage = 1L;
		Long limit = 5L;
		Long maxPage;
		Long startPage;
		Long endPage;
		Long blockStartNum = 1L;
		Long blockLastNum = 5L;
		Long listCount;
		String keyword = "";

		if (pi.getCurrentPage() != null) {
			currentPage = pi.getCurrentPage();
		}

		if (pi.getBlockStartNum() != null && pi.getBlockLastNum() != null) {
			blockStartNum = pi.getBlockStartNum();
			blockLastNum = pi.getBlockLastNum();
		}
		
		if (currentPage > blockLastNum) {
			blockStartNum = blockStartNum + 5;
			blockLastNum = blockLastNum + 5;
		} else if (currentPage < blockStartNum) {
			blockStartNum = blockStartNum - 5;
			blockLastNum = blockLastNum - 5;
		}

		if (pi.getKeyword() != null) {
			keyword = pi.getKeyword();
		}

		listCount = boardRepository.searchBoardListCount(keyword);

		maxPage = (long) ((double) listCount / limit + 0.9);
		startPage = (currentPage - 1) * limit + 1;
		endPage = startPage + limit - 1;
		return new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount, blockStartNum, blockLastNum,
				keyword);
	}

	public List<BoardVo> getList(PageInfo pi) {
		return boardRepository.searchBoardRecord(pi);

	}

	public boolean increaseGroupOrderNo(BoardVo vo) {
		return boardRepository.updateOrderNo(vo) == 1;
	}

	public Long addContents(BoardVo vo) {
		return boardRepository.insert(vo);
	}

	public BoardVo getContents(Long no) {
		BoardVo vo = boardRepository.findByNo(no);

		if (vo != null) {
			boardRepository.updateHit(no);
		}
		return vo;
	}

	public boolean deleteContents(Long no, Long userNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("no", no);
		map.put("userNo", userNo);

		Long groupCount = boardRepository.findBoardGroup(no);
		if (groupCount > 1L) {
			return boardRepository.findByNoAndUserNoUpdate(map) == 1;
		}
		return boardRepository.findByNoAndUserNo(map) == 1;
	}

	public int modifyContents(BoardVo vo) {
		return boardRepository.update(vo);
	}

}

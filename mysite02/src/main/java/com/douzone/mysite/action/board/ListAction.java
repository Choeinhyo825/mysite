package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageInfo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long currentPage;
		long limit;
		long maxPage;
		long startPage;
		long endPage;
		long blockStartNum;
		long blockLastNum;
		long listCount;
		String kwd;

		currentPage = 1;
		blockStartNum = 1;
		blockLastNum = 5;

		if (request.getParameter("currentPage") != null) {
			currentPage = Long.parseLong((request.getParameter("currentPage")));
		}

		if (request.getParameter("bsn") != null && request.getParameter("bln") != null) {
			blockStartNum = Long.parseLong((request.getParameter("bsn")));
			blockLastNum = Long.parseLong((request.getParameter("bln")));
		}

		if (currentPage > blockLastNum) {
			blockStartNum = blockStartNum + 5;
			blockLastNum = blockLastNum + 5;
		} else if (currentPage < blockStartNum && currentPage < blockLastNum) {
			blockStartNum = blockStartNum - 5;
			blockLastNum = blockLastNum - 5;
		}

		kwd = request.getParameter("kwd");

		listCount = new BoardRepository().searchBoardListCount(kwd);

		limit = 5;
		maxPage = (long) ((double) listCount / limit + 0.9);
		startPage = (currentPage - 1) * limit + 1;
		endPage = startPage + limit - 1;

		PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount, blockStartNum,
				blockLastNum);

		List<BoardVo> list = new BoardRepository().searchBoardRecord(pi,kwd);

		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("kwd", kwd);
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}

}

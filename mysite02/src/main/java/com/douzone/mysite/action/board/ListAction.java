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

		currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Long.parseLong((request.getParameter("currentPage")));
		}

		limit = 5;
		long listCount = new BoardRepository().searchBoardListCount();
		maxPage = (long) ((double) listCount / limit + 0.9);
		startPage = (currentPage - 1) * limit + 1;
		endPage = startPage + limit - 1;
		
		

		PageInfo pi = new PageInfo(currentPage, limit, maxPage, startPage, endPage, listCount);
		System.out.println(pi);

		List<BoardVo> list = new BoardRepository().searchBoardRecord(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}

}

package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("boardNo") == null || request.getParameter("userNo") == null
				|| request.getParameter("title") == null || request.getParameter("content") == null) {
			
			WebUtil.forward("/WEB-INF/views/main/index.jsp", request, response);
			return;
		}

		long boardNo = Long.parseLong(request.getParameter("boardNo"));
		long userNo = Long.parseLong(request.getParameter("userNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVo vo = new BoardVo();
		vo.setNo(boardNo);
		vo.setUserNo(userNo);
		vo.setTitle(title);
		vo.setContents(content);

		new BoardRepository().modify(vo);
		WebUtil.redirect(request.getContextPath() + "/board?a=view&no=" + boardNo, request, response);
	}

}

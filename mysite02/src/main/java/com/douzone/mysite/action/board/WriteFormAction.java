package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session == null) {
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		UserVo loginUser = (UserVo)session.getAttribute("loginUser");
		if(loginUser == null) {
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		if (null != request.getParameter("boardNo") && null != request.getParameter("gno")
				&& null != request.getParameter("ono") && null != request.getParameter("depth")) {

			long boardNo = Long.parseLong(request.getParameter("boardNo"));
			long gno = Long.parseLong(request.getParameter("gno"));
			long ono = Long.parseLong(request.getParameter("ono"));
			long depth = Long.parseLong(request.getParameter("depth"));

			BoardVo vo = new BoardVo();

			vo.setNo(boardNo);
			vo.setGno(gno);
			vo.setOno(ono);
			vo.setDepth(depth);

			request.setAttribute("vo", vo);
		}

		WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
	}

}

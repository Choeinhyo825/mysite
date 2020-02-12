package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteFormAction implements Action {

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
		
		long no = Long.parseLong(request.getParameter("no"));
		request.setAttribute("no", no);
		WebUtil.forward("/WEB-INF/views/board/deleteForm.jsp", request, response);
		
	}

}

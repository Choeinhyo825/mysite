package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접근제어(Access Control List, ACL)
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
		
		UserVo userVo = new UserRepository().getFindByNo(loginUser.getNo());
		
		request.setAttribute("userVo", userVo);
		
		WebUtil.forward("/WEB-INF/views/user/updateform.jsp", request, response);
	}

}

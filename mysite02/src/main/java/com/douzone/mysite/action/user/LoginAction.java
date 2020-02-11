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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPass(pass);

		UserVo userVo = new UserRepository().login(vo);
		if (userVo == null) {
			// 로그인 실패
			request.setAttribute("result", "fail");
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}
		// 로그인 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("loginUser", userVo);
		
		WebUtil.redirect(request.getContextPath(), request, response);
	}

}

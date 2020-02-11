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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("password");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");

		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setPass(pass);
		vo.setGender(gender);
		vo.setEmail(email);

		new UserRepository().update(vo);
		
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo)session.getAttribute("loginUser");
		userVo.setName(name);
		session.setAttribute("loginUser", userVo);
		
		WebUtil.redirect(request.getContextPath()+"/user?a=updatesuccess", request, response);
	}

}

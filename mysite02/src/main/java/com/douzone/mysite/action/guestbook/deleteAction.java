package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class deleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		String pass = request.getParameter("password");

		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPass(pass);

		new GuestbookRepository().delete(vo);
		WebUtil.redirect(request.getContextPath() + "/gb?a=list", request, response);
		
	}

}

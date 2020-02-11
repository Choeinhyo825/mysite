package com.douzone.mysite.action.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int visitCount = 0;

		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie coo : cookies) {
				if ("visitCount".equals(coo.getName())) {
					visitCount = Integer.parseInt(coo.getValue());
				}
			}
		}

		/*
		 * 처름이면 쿠키가 없기때문에 위를 거치지 않음. 
		 * 처음이 아니면 위를 돌고 다음 쿠키를 ++해서 새로 쿠키를 생성함
		 */

		// 쿠키 쓰기(굽기)
		Cookie cookie = new Cookie("visitCount", String.valueOf(++visitCount));
		cookie.setMaxAge(24 * 60 * 60); // 하루 지정
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);

		WebUtil.forward("/WEB-INF/views/main/index.jsp", request, response);
	}

}

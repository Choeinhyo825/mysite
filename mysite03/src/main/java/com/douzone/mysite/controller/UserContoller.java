package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserContoller {
	
	@Autowired
	private UserService userService;

	/* 
	 * 회원가입 폼
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	/* 
	 * 회원가입
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	/* 
	 * 회원가입 성공 폼
	 */
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	/* 
	 * 로그인 폼
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	/* 
	 * 로그인
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute UserVo vo) {
		UserVo authUser = userService.getUser(vo);
		if(authUser == null) {
			return "user/login";
		}
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	/* 
	 * 로그아웃
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		//////////////////////////// 접근제어 ///////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////////////////////////////////////////////
		
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	/* 
	 * 회원정보 수정 폼
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		
		//////////////////////////// 접근제어 ///////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////////////////////////////////////////////
		
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo",vo);
		return "/user/update";
	}
	/* 
	 * 회원정보 수정
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, UserVo vo) {
		
		//////////////////////////// 접근제어 ///////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////////////////////////////////////////////
		
		 userService.updateUser(vo);
		return "/user/updatesuccess";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String handleException() {
//		return "error/exception";
//	}
}

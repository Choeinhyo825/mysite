package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		UserVo loginUser = userService.getUser(vo);
		if(loginUser == null) {
			return "user/login";
		}
		session.setAttribute("loginUser", loginUser);
		return "redirect:/";
	}
	/* 
	 * 로그아웃
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUSer");
		session.invalidate();
		return "redirect:/";
	}
	/* 
	 * 회원정보 수정 폼
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		UserVo vo = (UserVo)session.getAttribute("loginUser");
		vo = userService.getUserInformation(vo.getNo());
		model.addAttribute("userVo",vo);
		return "/user/update";
	}
	/* 
	 * 회원정보 수정
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(UserVo vo) {
		 userService.updateUser(vo);
		return "/user/updatesuccess";
	}
}

package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserContoller {

	@Autowired
	private UserService userService;

	/*
	 * 회원가입 폼
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {

		return "user/join";
	}

	/*
	 * 회원가입
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if (result.hasErrors()) {

			// List<ObjectError> list = result.getAllErrors();
			// for(ObjectError error:list) {
			// System.out.println(error);
			// }
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
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
	 * 회원정보 수정 폼
	 */
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, @AuthUser UserVo authUser) {
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		return "/user/update";
	}

	/*
	 * 회원정보 수정
	 */
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(UserVo vo, @AuthUser UserVo authUser) {
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());
		return "/user/updatesuccess";
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public void auth() {
		
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void lotout() {
		
	}
}

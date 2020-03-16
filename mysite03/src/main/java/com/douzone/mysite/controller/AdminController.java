package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping({ "", "/main" })
	public String main(Model model) {
		SiteVo siteVo = adminService.selectProfile();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}

	@RequestMapping(value = "updateProfile", method = RequestMethod.POST)
	public String upload(SiteVo vo, @RequestParam(value = "titleImage") MultipartFile multipartFile, Model model) {
		adminService.restore(vo, multipartFile);
		return "redirect:main";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

}

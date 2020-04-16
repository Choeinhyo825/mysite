package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired GuestBookService guestBookService;
	
	@RequestMapping({"","/list"})
	public String list(Model model) {
		List<GuestbookVo> list = guestBookService.getList();
		model.addAttribute("list",list);
		return "guestbook/list";
	}
	@RequestMapping("/spa")
	public String indexSpa(Model model) {
//		List<GuestbookVo> list = guestBookService.getList();
//		model.addAttribute("list",list);
		return "guestbook/index-spa";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Model model,GuestbookVo vo) {
		guestBookService.insertGuestBook(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no) {
		return "guestbook/delete";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(value = "pass", required = true, defaultValue = "") String pass) {
		guestBookService.delete(no, pass);
		return "redirect:/guestbook/list";
	}
	

}

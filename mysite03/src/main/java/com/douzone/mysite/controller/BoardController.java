package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.PageInfo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping({"","/list"})
	public String list(Model model, PageInfo pi) {
//		List<BoardVo> list = boardService.getList(pi);
//		model.addAttribute("list",list);
		return "board/list";
	}
}

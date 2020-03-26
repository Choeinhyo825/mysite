package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageInfo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@RequestMapping("")
	public String list(Model model, @RequestParam(value = "p", required = true, defaultValue = "1") Long currentPage,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword,
			@RequestParam(value = "bsn", required = true, defaultValue = "1") Long blockStartNum,
			@RequestParam(value = "bln", required = true, defaultValue = "5") Long blockLastNum) {

		PageInfo pi = new PageInfo();
		pi.setCurrentPage(currentPage);
		pi.setKeyword(keyword);
		pi.setBlockStartNum(blockStartNum);
		pi.setBlockLastNum(blockLastNum);

		pi = boardService.getCount(pi);

		List<BoardVo> list = boardService.getList(pi);
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		return "board/list";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, @ModelAttribute BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		if (vo.getGno() != null) {
			boardService.increaseGroupOrderNo(vo);
		}
		Long no = boardService.addContents(vo);
		return "redirect:/board/view/" + no;
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getContents(no);
		model.addAttribute("vo", boardVo);
		return "board/view";
	}

	@Auth
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, @AuthUser UserVo authUser) {
		boardService.deleteContents(no, authUser.getNo());
		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value = "/reply/{no}")
	public String reply(@PathVariable("no") Long no, Model model) {

		BoardVo vo = boardService.getContents(no);
		vo.setOno(vo.getOno() + 1);
		vo.setDepth(vo.getDepth() + 1);
		model.addAttribute("vo", vo);

		return "board/reply";
	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/modify";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, @ModelAttribute BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		boardService.modifyContents(vo);
		return "redirect:/board/view/" + vo.getNo();
	}

}

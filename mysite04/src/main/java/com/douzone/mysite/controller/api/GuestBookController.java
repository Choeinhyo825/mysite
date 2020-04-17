package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestbookVo;

@RestController("GuestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestBookController {

	@Autowired
	GuestBookService guestBookService;

	@GetMapping("/list/{no}")
	public JsonResult list(@PathVariable("no") Long startNo) {
		List<GuestbookVo> list = guestBookService.getMessageList(startNo);
		return JsonResult.success(list);
	}

	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestbookVo vo) {
		guestBookService.insertGuestBook(vo);
		vo.setPass("");
		return JsonResult.success(vo);
	}

	@DeleteMapping("/delete/{no}")
	public JsonResult delete(@PathVariable("no") Long no,
			@RequestParam(value = "password", required = true, defaultValue = "") String pass) {
		boolean result = guestBookService.delete(no, pass);
		return JsonResult.success(result ? no : -1);
	}

}

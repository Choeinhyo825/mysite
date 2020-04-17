package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getList() {
		return guestbookRepository.findAll();
	}

	public Boolean insertGuestBook(GuestbookVo vo) {
		return 1 == guestbookRepository.insert(vo);
	}

	public Boolean delete(Long no, String pass) {
		return 1 == guestbookRepository.delete( new GuestbookVo(no, pass) );
		
	}

	public List<GuestbookVo> getMessageList(Long startNo) {
		return guestbookRepository.findAll(startNo);
	}


}

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
	private GuestbookRepository GuestbookRepository;

	public List<GuestbookVo> getList() {
		return GuestbookRepository.findAll();
	}

	public Boolean insertGuestBook(GuestbookVo vo) {
		int count = GuestbookRepository.insert(vo);
		return count == 1;
	}

	public Boolean delete(Long no, String pass) {
		int count = GuestbookRepository.delete(no,pass);
		return count == 1;
		
	}


}

package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo vo = new BoardVo();

		long userNo = Long.parseLong(request.getParameter("userNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		vo.setUserNo(userNo);
		vo.setTitle(title);
		vo.setContents(content);

		if ("" != request.getParameter("boardNo") && "" != request.getParameter("gno")
				&& "" != request.getParameter("ono") && "" != request.getParameter("depth")) {

			long boardNo = Long.parseLong(request.getParameter("boardNo"));
			long gno = Long.parseLong(request.getParameter("gno"));
			long ono = Long.parseLong(request.getParameter("ono"));
			long depth = Long.parseLong(request.getParameter("depth"));

			vo.setNo(boardNo);
			vo.setGno(gno);
			vo.setOno(ono);
			vo.setDepth(depth);
			
			new BoardRepository().updateList(vo);
			new BoardRepository().insertReply(vo);
			WebUtil.redirect(request.getContextPath() + "/board?a=list", request, response);
			
			return;
		}

		new BoardRepository().write(vo);
		WebUtil.redirect(request.getContextPath() + "/board?a=list", request, response);
	}

}

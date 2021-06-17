package controller.notice;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import model.DAO.NoticeDAO;
import model.DTO.NoticeDTO;

public class NoticeModifyAction {

	public void noticeModify(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		NoticeDTO dto = new NoticeDTO();
		
		dto.setNoticeCon(request.getParameter("noticeCon"));
		dto.setNoticeFile(request.getParameter("noticeFile"));
		dto.setNoticeSub(request.getParameter("noticeSub"));
		
		NoticeDAO dao = new NoticeDAO();
		dao.noticeModify(dto);
	}
}

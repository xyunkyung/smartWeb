package controller.notice;

import javax.servlet.http.HttpServletRequest;

import model.DAO.NoticeDAO;
import model.DTO.NoticeDTO;

public class NoticeDetailAction {

	public void noticeDetail(HttpServletRequest request) {
		String noticeNo = request.getParameter("noticeNo");
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = dao.noticeOne(noticeNo);
		request.setAttribute("dto", dto);
	}
}

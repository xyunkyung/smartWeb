package controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.NoticeDAO;
import model.DTO.AuthInfo;

public class NoticeRegestAction {

	public void noticeRegest(HttpServletRequest request) {
		NoticeDAO dao = new NoticeDAO();
		String noticeNo = dao.getNoticeNum();
		request.setAttribute("noticeNo", noticeNo);
	}
}

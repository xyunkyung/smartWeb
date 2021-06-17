package controller.notice;

import javax.servlet.http.HttpServletRequest;

import model.DAO.NoticeDAO;

public class NoticeRegestAction {

	public void noticeRegest(HttpServletRequest request) {
		NoticeDAO dao = new NoticeDAO();
		int noticeNum = dao.getNoticeNum();
		request.setAttribute("noticeNum", noticeNum);
	}
}

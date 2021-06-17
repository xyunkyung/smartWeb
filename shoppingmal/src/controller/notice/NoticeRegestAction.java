package controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.NoticeDAO;
import model.DTO.AuthInfo;

public class NoticeRegestAction {

	public void noticeRegest(HttpServletRequest request) {
		NoticeDAO dao = new NoticeDAO();
		int noticeNo = dao.getNoticeNum();
		request.setAttribute("noticeNo", noticeNo);
		
	    HttpSession session=request.getSession();
	      AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
	      String empId=authInfo.getGrade();
	      request.setAttribute("empId", empId);
	}
}

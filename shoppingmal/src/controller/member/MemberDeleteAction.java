package controller.member;

import javax.servlet.http.HttpServletRequest;

import model.DAO.MemberDAO;

public class MemberDeleteAction {

	public void memDel(HttpServletRequest request) {
		String memId = request.getParameter("memId");
		MemberDAO dao = new MemberDAO();
		dao.memDel(memId);
	}
}

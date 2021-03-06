package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;

public class MemberPwChangeAction {

	public int pwChange(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		String userId = authInfo.getUserId();
		String memPw = request.getParameter("newPw");
		
		if(request.getParameter("memPw").equals(authInfo.getUserPw()) ) {
			MemberDAO dao = new MemberDAO();
			dao.pwChange(userId, memPw);
			return 1;
		} else {
			request.setAttribute("pwFail", "비밀번호가 일치하지 않습니다.");
			return 2;
		}
	}
}

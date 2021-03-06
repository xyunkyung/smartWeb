package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DTO.AuthInfo;

public class MemberPwConfirmAction {

	public String pwConfirm(HttpServletRequest request) {
		String path = null;
		HttpSession session = request.getSession();
		AuthInfo authInfo  = (AuthInfo)session.getAttribute("authInfo");
		
		if(request.getParameter("memPw").equals(authInfo.getUserPw())) {
			path = "member/pwChangeOk.jsp";
		} else {
			request.setAttribute("pwFail", "비밀번호가 일치하지 않습니다.");
			path = "member/pwChange.jsp";
		}
		return path;
	}
}

package controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DTO.AuthInfo;

public class EmployeePwConfirmAction {

	public String empPwConfirm(HttpServletRequest request) {
		String path = null;
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		if(request.getParameter("empPw").equals(authInfo.getUserPw())) {
			path = "employee/empPwChangeOk.jsp";
		} else {
			session.setAttribute("pwFail", "비밀번호가 일치하지 않습니다.");
			path = "employee/empPwChange.jsp";
		}
		return path;
	}
}

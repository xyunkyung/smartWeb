package controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.EmployeeDAO;
import model.DAO.MemberDAO;
import model.DTO.AuthInfo;

public class EmployeePwChangeAction {

	public int empPwChange(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		String userId = authInfo.getUserId();
		String empPw = request.getParameter("newPw");
		
		if(request.getParameter("empPw").equals(authInfo.getUserPw())) {
			EmployeeDAO dao = new EmployeeDAO();
			dao.empPwChange(userId, empPw);
			return 1;
		} else {
			session.setAttribute("pwFail", "비밀번호가 일치하지 않습니다.");
			return 2;
		}
	}
}

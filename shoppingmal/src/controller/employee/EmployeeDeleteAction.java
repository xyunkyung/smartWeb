package controller.employee;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;

public class EmployeeDeleteAction {

	public void empDelete(HttpServletRequest request) {
		String empId = request.getParameter("empId");
		EmployeeDAO dao = new EmployeeDAO();
		dao.empDelete(empId);
	}
}

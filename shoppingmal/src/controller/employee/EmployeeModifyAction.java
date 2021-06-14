package controller.employee;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;
import model.DTO.EmployeeDTO;

public class EmployeeModifyAction {

	public void empModify(HttpServletRequest request) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeId(request.getParameter("employeeId"));
		dto.setEmail(request.getParameter("email"));
		dto.setEmpAddress(request.getParameter("empAddress"));
		dto.setJobId(request.getParameter("jobId"));
		dto.setPhNumber(request.getParameter("phNumber"));
		dto.setOfficeNumber(request.getParameter("officeNumber"));
		
		EmployeeDAO dao = new EmployeeDAO();
		dao.empUpdate(dto);
	}
}

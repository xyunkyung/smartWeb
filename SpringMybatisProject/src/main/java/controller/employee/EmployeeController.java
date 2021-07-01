package controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.employee.EmployeeNumService;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeNumService employeeNumService;
	@RequestMapping(value="empList", method=RequestMethod.GET)
	public String empList(Model model) {
		employeeNumService.empNo(model);
		return "employee/employeeForm";
	}
}

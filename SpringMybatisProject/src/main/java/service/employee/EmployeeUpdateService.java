package service.employee;

import org.springframework.beans.factory.annotation.Autowired;

import Model.EmployeeDTO;
import command.EmployeeCommand;
import repository.EmployeeRepository;

public class EmployeeUpdateService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public void empUpdate(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmail(employeeCommand.getEmail());
		dto.setEmpAddress(employeeCommand.getEmpAddress());
		dto.setJobId(employeeCommand.getJobId());
		dto.setOfficeNumber(employeeCommand.getOfficeNumber());
		dto.setPhNumber(employeeCommand.getPhNumber());
		dto.setEmployeeId(employeeCommand.getEmployeeId());
		System.out.println(dto.getEmail());
		System.out.println(dto.getEmpAddress());
		System.out.println(dto.getEmployeeId());
		System.out.println(dto.getJobId());
		System.out.println(dto.getOfficeNumber());
		System.out.println(dto.getPhNumber());
		employeeRepository.empUpdate(dto);
	}
}

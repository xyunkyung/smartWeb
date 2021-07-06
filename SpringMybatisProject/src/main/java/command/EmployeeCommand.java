package command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeCommand {
	String employeeId;
	String empUserid;
	String empPw;
	String empPwCon;
	String empName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date hireDate;
	String jobId;
	String phNumber;
	String officeNumber;
	String email;
	String empAddress;
	
	public boolean isEmpPwEqualsEmpPwCon() {
		return empPw.equals(empPwCon);
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public String getEmpUserid() {
		return empUserid;
	}
	public String getEmpPw() {
		return empPw;
	}
	public String getEmpPwCon() {
		return empPwCon;
	}
	public String getEmpName() {
		return empName;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public String getJobId() {
		return jobId;
	}
	public String getPhNumber() {
		return phNumber;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public void setEmpUserid(String empUserid) {
		this.empUserid = empUserid;
	}
	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}
	public void setEmpPwCon(String empPwCon) {
		this.empPwCon = empPwCon;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	
}

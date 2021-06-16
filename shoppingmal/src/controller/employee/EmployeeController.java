package controller.employee;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;

public class EmployeeController extends HttpServlet
	implements Servlet{
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		if(command.equals("/empList.em")) {
			EmployeeListAction action = new EmployeeListAction();
			action.empList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeList.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empRegest.em")) {
			EmployeeNumAction action = new EmployeeNumAction();
			action.getNum(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeForm.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empJoin.em")) {
			EmployeeJoinAction action = new EmployeeJoinAction();
			action.empInsert(request);
			response.sendRedirect("empList.em");
		} else if(command.equals("/empInfo.em")) {
			EmployeeInfoAction action = new EmployeeInfoAction();
			action.empInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeInfo.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/empModify.em")) {
			EmployeeInfoAction action = new EmployeeInfoAction();
			action.empInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeModify.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/empModifyOk.em")) {
			EmployeeModifyAction action = new EmployeeModifyAction();
			action.empModify(request);
			response.sendRedirect("empList.em");	// 페이지 이동
		} else if(command.equals("/empDelete.em")) {
			EmployeeDeleteAction action = new EmployeeDeleteAction();
			action.empDelete(request);
			response.sendRedirect("empList.em");
		} else if(command.equals("/empMyPage.em")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/empMyPage.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/empDetail.em")) {
			EmployeeDetailAction action = new EmployeeDetailAction();
			action.empDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/empDetail.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/empSujung.em")) {
			EmployeeDetailAction action = new EmployeeDetailAction();
			action.empDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/empSujung.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/empSujungOk.em")) {
			EmployeeUpdateAction action = new EmployeeUpdateAction();
			int i = action.employeeUpdate(request);
			if(i == 1) {
				response.sendRedirect("empDetail.em");
			} else {
				response.sendRedirect("empSujung.em");
			}
		} else if(command.equals("/empPwChange.em")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/empPwChange.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/pwChangeOk.em")) {
			EmployeePwConfirmAction action = new EmployeePwConfirmAction();
			String path = action.empPwConfirm(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} else if(command.equals("/empChangePw.em")) {
			EmployeePwChangeAction action = new EmployeePwChangeAction();
			int i = action.empPwChange(request);
			if(i == 1) {
				response.sendRedirect("main.sm");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("employee/empPwChange.jsp");
				dispatcher.forward(request, response);
			}
		} else if(command.equals("/empOut.em")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/empOutPw.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/empOutOk.em")) {
			EmployeeOutAction action = new EmployeeOutAction();
			int i = action.empOut(request);
			if(i == 1) {
				response.sendRedirect("main.sm");
			} else {
				response.sendRedirect("empOut.em");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
}
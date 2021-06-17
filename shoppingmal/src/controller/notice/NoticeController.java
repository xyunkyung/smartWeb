package controller.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeController extends HttpServlet implements Servlet {

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		if(command.equals("/noticeList.nc")) {
			NoticeListAction action = new NoticeListAction();
			action.noticeList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("notice/noticeList.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/noticeRegest.nc")) {
			NoticeRegestAction action = new NoticeRegestAction();
			action.noticeRegest(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("notice/noticeRegest.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/noticeRegestOk.nc")) {
			NoticeRegestOkAction action = new NoticeRegestOkAction();
			action.noticeRegestOk(request);
			response.sendRedirect("noticeList.nc");
		} else if(command.equals("/noticeDetail.nc")) {
			NoticeDetailAction action = new NoticeDetailAction();
			action.noticeDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("notice/noticeDetail.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/noticeModify.nc")) {
			NoticeModifyAction action = new NoticeModifyAction();
			action.noticeModify(request);
			response.sendRedirect("noticeList.nc");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
}

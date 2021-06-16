package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;

public class MemberController extends HttpServlet implements Servlet {

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		if(command.equals("/memAgree.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/agree.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/memRegist.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberForm.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/memJoin.mem")) {
			MemberJoinAction action = new MemberJoinAction();
			action.memInsert(request);
			response.sendRedirect("main.sm");
		} else if(command.equals("/memList.mem")) {
			MemberListAction action = new MemberListAction();
			action.memList(request);
			response.setCharacterEncoding("utf-8");		// 보낼 때 깨지면 response 받을 때 깨지면 request
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberList.jsp");
			dispatcher.include(request, response);
		} else if(command.equals("/memInfo.mem")) {
			MemberInfoAction action = new MemberInfoAction();
			action.memInfo(request);
			response.setCharacterEncoding("utf-8");	
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberInfo.jsp");
			dispatcher.forward(request, response);		// 빈 주소라 forward나 include 어떤 거 사용해도 상관 없음
		} else if(command.equals("/memMod.mem")) {
			MemberInfoAction action = new MemberInfoAction();
			action.memInfo(request);
			RequestDispatcher distpatcher = request.getRequestDispatcher("member/memberModify.jsp");
			distpatcher.forward(request, response);
		} else if(command.equals("/memModifyOk.mem")) {
			MemberModifyAction action = new MemberModifyAction();
			action.memUpdate(request);
			response.sendRedirect("memList.mem");
		} else if(command.equals("/memDel.mem")) {
			MemberDeleteAction action = new MemberDeleteAction();
			action.memDel(request);
			response.sendRedirect("memList.mem");
		} else if(command.equals("/myPage.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memMyPage.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/memDetail.mem")) {
			MemberDetailAction action = new MemberDetailAction();
			action.memberDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memDetail.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/memSujung.mem")) {
			MemberDetailAction action = new MemberDetailAction();
			action.memberDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memSujung.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/memSujungOk.mem")) {
			MemberUpdateAction action = new MemberUpdateAction();
			int i = action.memberUpdate(request);
			if(i == 1) {
				response.sendRedirect("memDetail.mem");
			} else {
				response.sendRedirect("memSujung.mem");
			}
		} else if(command.equals("/memOut.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/outPw.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/memOutOk.mem")) {
			MemberOutAction action = new MemberOutAction();
			int i = action.memOut(request);
			if(i == 1) {
				response.sendRedirect("main.sm");
			} else {
				response.sendRedirect("memOut.mem");
			}
		} else if(command.equals("/memPwChange.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/pwChange.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/pwChangeOk.mem")) {
			MemberPwConfirmAction action = new MemberPwConfirmAction();
			String path = action.pwConfirm(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} else if(command.equals("/ChangePw.mem")) {
			MemberPwChangeAction action = new MemberPwChangeAction();
			int i = action.pwChange(request);
			if(i == 1) {
				response.sendRedirect("main.sm");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("member/pwChange.jsp");
				dispatcher.forward(request, response);
			}
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

package controller.venta;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VentaController extends HttpServlet implements Servlet {
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		if(command.equals("/venta.vnt")) {
			ClientSalesAction action = new ClientSalesAction();
			action.clientSale(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/venta.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/userSales.vnt")) {
			UserSalesAction action = new UserSalesAction();
			action.userSale(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/userSales.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/customerTotal.vnt")) {
			CustomerTotalAction action = new CustomerTotalAction();
			action.customerTotal(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/customerTotal.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/createDelivery.vnt")) {
			CreateDeliveryAction action = new CreateDeliveryAction();
			action.execute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/deliveryForm.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/deliveryOk.vnt")) {
			DeliveryOkAction action = new DeliveryOkAction();
			action.execute(request);
			response.sendRedirect("venta.vnt");
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

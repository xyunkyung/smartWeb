package controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoodsController extends HttpServlet implements Servlet {

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		if(command.equals("/goodsList.gd")) {
			GoodsListAction action = new GoodsListAction();
			action.goodsList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsList.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/goodsRegist.gd")) {
			GoodsNumberAction action = new GoodsNumberAction();
			action.goodsNum(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsJoin.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/goodsJoin.gd")) {
			GoodsJoinAction action = new GoodsJoinAction();
			action.goodsJoin(request);
			response.sendRedirect("goodsList.gd");
		} else if(command.equals("/prodDetail.gd")) {
			GoodsModifyAction action = new GoodsModifyAction();
			action.goodsModify(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsModify.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/goodsModify.gd")) {
			GoodsUpdateAction action = new GoodsUpdateAction();
			action.goodsUpdate(request);
			response.sendRedirect("goodsList.gd");
		} else if(command.equals("/prodDel.gd")) {
			GoodsDeleteAction action = new GoodsDeleteAction();
			action.goodsDelete(request);
			response.sendRedirect("goodsList.gd");
		} else if(command.equals("/prodInfo.gd")) {
			response.setCharacterEncoding("utf-8");
			GoodsModifyAction action = new GoodsModifyAction();
			action.goodsModify(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsInfo.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/goodsCartAdd.gd")) {
			GoodsCartAddAction action = new GoodsCartAddAction();
			action.cartAdd(request);
			response.sendRedirect("goodsCartList.gd");
			
		} else if(command.equals("/goodsCartList.gd")) {
			GoodsCartList cartList = new GoodsCartList();
			cartList.cartList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsCart.jsp");
			dispatcher.forward(request, response);
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

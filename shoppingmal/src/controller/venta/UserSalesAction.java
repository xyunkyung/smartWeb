package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.ClientSaleDAO;
import model.DTO.ClientSaleDTO;

public class UserSalesAction {

	public void userSale(HttpServletRequest request) {
		String memId = request.getParameter("memId");
		ClientSaleDAO dao = new ClientSaleDAO();
		List<ClientSaleDTO> list = dao.salesList(memId);
		request.setAttribute("list", list);
	}
}

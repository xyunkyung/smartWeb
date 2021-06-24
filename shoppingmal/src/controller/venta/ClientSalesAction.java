package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.ClientSaleDAO;
import model.DTO.ClientSaleDTO;

public class ClientSalesAction {

	public void clientSale(HttpServletRequest request) {
		String memId = request.getParameter("memId");
		if(memId == "") {
			memId = null;
		}
		
		ClientSaleDAO dao = new ClientSaleDAO();
		List<ClientSaleDTO> list = dao.salesList(memId);
		request.setAttribute("list", list);
	}
}

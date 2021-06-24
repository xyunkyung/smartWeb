package controller.venta;

import javax.servlet.http.HttpServletRequest;

import model.DAO.ClientSaleDAO;
import model.DTO.DeliveryDTO;

public class CreateDeliveryAction {

	public void execute(HttpServletRequest request) {
		ClientSaleDAO dao = new ClientSaleDAO();
		DeliveryDTO dto = dao.deliverySelect(request.getParameter("purchaseNum"));
		request.setAttribute("purchaseNum", request.getParameter("purchaseNum"));
		request.setAttribute("dto", dto);
	}
}

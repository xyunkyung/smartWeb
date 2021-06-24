package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.ClientSaleDAO;
import model.DTO.CustomerTotalDTO;

public class CustomerTotalAction {

	public void customerTotal(HttpServletRequest request) {
		ClientSaleDAO dao = new ClientSaleDAO();
		List<CustomerTotalDTO> list = dao.customerTotal();
		
		String googleList = "[['아이디/이름', '총 구매금액', '총 횟수', '평균 금액']";
		for(CustomerTotalDTO dto : list) {
			googleList += ", ['" + dto.getMemId() + "/" + dto.getMemName() + "', " + dto.getSumPrice() + ", " + dto.getCount() + ", " + dto.getAvg() + "]";
		}
		googleList += "]";
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}

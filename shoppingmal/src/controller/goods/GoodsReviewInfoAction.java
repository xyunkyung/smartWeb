package controller.goods;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;
import model.DTO.GoodsReviewDTO;

public class GoodsReviewInfoAction {

	public void reviewInfo(HttpServletRequest request) {
		GoodsReviewDTO dto = new GoodsReviewDTO();
		dto.setProdNum(request.getParameter("prodNum"));
		dto.setPurchaseNum(request.getParameter("purchaseNum"));
		GoodsDAO dao = new GoodsDAO();
		dao.reviewSelect(dto);
		request.setAttribute("dto", dto);
	}
}

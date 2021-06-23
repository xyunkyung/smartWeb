package controller.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;
import model.DTO.GoodsDTO;
import model.DTO.ProdReviewDTO;

public class GoodsModifyAction {

	public void goodsModify(HttpServletRequest request) {
		String prodNum = request.getParameter("prodNum");
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO dto = dao.GoodsOne(prodNum);
		List<ProdReviewDTO> list = dao.prodReviewSelect(prodNum);
		request.setAttribute("list", list);
		request.setAttribute("dto", dto);
	}
}

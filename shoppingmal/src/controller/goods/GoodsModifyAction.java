package controller.goods;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;
import model.DTO.GoodsDTO;

public class GoodsModifyAction {

	public void goodsModify(HttpServletRequest request) {
		String prodNum = request.getParameter("prodNum");
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO dto = dao.GoodsOne(prodNum);
		request.setAttribute("dto", dto);
	}
}

package controller.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;
import model.DTO.GoodsDTO;

public class GoodsListAction {

	public void goodsList(HttpServletRequest request) {
		GoodsDAO dao = new GoodsDAO();
		List<GoodsDTO> list = dao.goodsList();
		request.setAttribute("lists", list);
	}
}

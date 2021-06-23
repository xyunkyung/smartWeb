package controller.goods;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;
import model.DTO.GoodsReviewDTO;

public class GoodsReviewWriteAction {

	public void reviewUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GoodsReviewDTO dto = new GoodsReviewDTO();
		dto.setProdNum(request.getParameter("prodNum"));
		dto.setPurchaseNum(request.getParameter("purchaseNum"));
		dto.setReviewContent(request.getParameter("reviewContent"));
		
		GoodsDAO dao = new GoodsDAO();
		dao.reviewUpdate(dto);
	}
}

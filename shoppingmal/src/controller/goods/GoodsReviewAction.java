package controller.goods;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.DAO.GoodsDAO;
import model.DTO.GoodsReviewDTO;

public class GoodsReviewAction {

	public void review(HttpServletRequest request) {
		
		String path = "goods/review";
		String realPath = request.getServletContext().getRealPath(path);
		int size = 1024*1024*5;
		MultipartRequest multi = null;
		
		GoodsReviewDTO dto = new GoodsReviewDTO();
		try {
			multi = new MultipartRequest(request, realPath, size, "utf-8", new DefaultFileRenamePolicy());
			dto.setReviewImg(multi.getFilesystemName("reviewImg"));
			dto.setProdNum(multi.getParameter("prodNum"));
			dto.setPurchaseNum(multi.getParameter("purchaseNum"));
			dto.setReviewContent(multi.getParameter("reviewContent"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GoodsDAO dao = new GoodsDAO();
		dao.reviewInsert(dto);
	}
}

package controller.notice;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import model.DAO.NoticeDAO;
import model.DTO.NoticeDTO;

public class noticeDeleteAction {

	public void noticeDelete(HttpServletRequest request) {
		String noticeNo = request.getParameter("noticeNo");
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = dao.noticeOne(noticeNo);
		
		String filePath = "notice/uplod";
		String realPath = request.getServletContext().getRealPath(filePath);
		
		String fileName = dto.getNoticeFile();
		if(fileName != null ) {
			String path = realPath + "/" + fileName;
			File f = new File(path);
			if(f.exists()) f.delete();
		}
		dao.goodsDelete(noticeNo);
	}
	
}

package controller.member;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.MemberDTO;

public class IdFindAction {

	public void idFind(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MemberDTO dto = new MemberDTO();
		dto.setMemName(request.getParameter("memName"));
		dto.setMemEmail(request.getParameter("memEmail"));
		dto.setMemPhone(request.getParameter("memPhone"));
		
		MemberDAO dao = new MemberDAO();
		dao.idFind(dto);
		
		if(dto.getMemId() == null) {
			request.setAttribute("idFail", "아이디가 존재하지 않습니다.");
		} else {
			request.setAttribute("dto", dto);
		}
	}
}

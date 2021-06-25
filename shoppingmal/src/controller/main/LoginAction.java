package controller.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.LoginDAO;
import model.DTO.AuthInfo;

public class LoginAction {

	public void login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		LoginDAO dao = new LoginDAO();
		AuthInfo authInfo = dao.login(userId);
		
		if(authInfo == null) {
			session.removeAttribute("pwFail");
			session.setAttribute("userFail", "아이디가 존재하지 않습니다.");	// session은 페이지 닫을 때 까지 살아있음
		} else {
			session.removeAttribute("userFail");
			if(userPw.equals(authInfo.getUserPw())) {
				session.removeAttribute("pwFail");
				session.setAttribute("authInfo", authInfo);
				
				// 자동로그인 체크박스
				String autoLogin = request.getParameter("autoLogin");
				if(autoLogin != null && autoLogin.equals("auto")) {
					Cookie cookie = new Cookie("autoLogin", userId);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}
				
				//쿠키 생성
				String idStore = request.getParameter("idStore");
				if(idStore != null && idStore.equals("store")) {
					Cookie cookie = new Cookie("idStore", userId);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("idStore", userId);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			} else {
				session.setAttribute("pwFail", "비밀번호가 일치하지 않습니다.");
			}
		}
	}
}

package service.main;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

import Model.AuthInfoDTO;
import command.LoginCommand;
import repository.LoginRepository;

public class LoginService {
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	public void logIn1(LoginCommand loginCommand, Errors errors,
			HttpSession session) {
		String userId = loginCommand.getUserId();
		AuthInfoDTO authInfo = loginRepository.login(userId);
		if(authInfo == null) {
			errors.rejectValue("userId", "notId");
		}else {
			if(bcryptPasswordEncoder.matches(
					loginCommand.getUserPw(), authInfo.getUserPw())) {
				session.setAttribute("authInfo", authInfo);
			}else {
				errors.rejectValue("userPw", "notPw");
			}
		}
	}
	public AuthInfoDTO logIn(String userId, String userPw) {
		AuthInfoDTO authInfo = loginRepository.login(userId); 
		return authInfo;
	}
	
}

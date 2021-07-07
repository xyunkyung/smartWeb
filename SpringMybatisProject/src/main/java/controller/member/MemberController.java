package controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Model.AuthInfoDTO;
import command.MemberCommand;
import service.main.LoginService;
import service.member.MemberEmailCkService;
import service.member.MemberJoinService;
import validator.MemberCommandValidator;

@Controller
@RequestMapping("register")

public class MemberController {

	@Autowired
	MemberJoinService memberJoinService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	MemberEmailCkService memberEmailCkService;
	
	@RequestMapping("memberMail")
	public String memberMail(@RequestParam(value="num") String num, @RequestParam(value="receiver") String receiver) {
		int i = memberEmailCkService.emailCk(receiver, num);
		
		if(i > 0) {
			return "member/success";
		} else {
			return "member/fail";
		}
	}
	
	@RequestMapping(value = "memJoin", method = RequestMethod.POST)
	public String memJoin(Model model, MemberCommand memberCommand,Errors errors) {
		new MemberCommandValidator().validate(memberCommand, errors);
		
		if(errors.hasErrors()) {
			return "member/memberForm";
		}
		
		AuthInfoDTO authInfo = loginService.logIn(memberCommand.getMemId(), memberCommand.getMemPw());
		if(authInfo != null) {
			errors.rejectValue("memId", "duplicate");
			return "member/memberForm";
		}
		memberJoinService.memJoin(memberCommand);
		return "redirect:/";
	}
	
	@RequestMapping(value = "memRegist", method = RequestMethod.POST)
	public String memRegist(@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
		@ModelAttribute(value = "memberCommand") MemberCommand memberCommand, Model model) {
		if(!agree) {
			model.addAttribute("err", "동의에 체크해주세요.");
			return "member/agree";
		}
		return "member/memberForm";
	}
	
	@RequestMapping("agree")
	public String agree() {
		return "member/agree";
	}
}

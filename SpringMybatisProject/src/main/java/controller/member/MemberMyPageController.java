package controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.MemberCommand;
import service.member.MemberInfoService;
import service.member.MemberUpdateService;

@Controller
@RequestMapping("edit")
public class MemberMyPageController {
	@Autowired
	MemberInfoService memberInfoService;
	
	@Autowired
	MemberUpdateService memberUpdateService;
	
	@RequestMapping("myPage")
	public String myPage() {
		return "member/memMyPage";
	}
	@RequestMapping("memDetail")
	public String memDetail(HttpSession session, Model model) {
		memberInfoService.memInfo(model, session);
		return "member/memDetail";
	}
	
	@RequestMapping("memSujung")
	public String memSujung(HttpSession session, Model model) {
		memberInfoService.memInfo(model, session);
		return "member/memSujung";
	}
	@RequestMapping(value = "memSujungOk", method = RequestMethod.POST)
	public String memSujungOk(MemberCommand memberCommand) {
		memberUpdateService.memUpdate(memberCommand);
		return "member/memDetail";
	}
}
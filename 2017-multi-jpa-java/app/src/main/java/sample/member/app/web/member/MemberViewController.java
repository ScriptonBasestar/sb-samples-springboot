package sample.member.app.web.member;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.member.app.security.SimpleUser;

@Controller
@RequestMapping("/member")
public class MemberViewController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list() {
		return "member-list.html";
	}

	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public String my(Model model) {
		SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", simpleUser.getUsername());
		return "member-detail.html";
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String detail(@PathVariable String username, Model model) {
		model.addAttribute("username", username);
		return "member-detail.html";
	}

}

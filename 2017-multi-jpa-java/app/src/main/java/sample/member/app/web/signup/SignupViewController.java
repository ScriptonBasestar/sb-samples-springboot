package sample.member.app.web.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupViewController {

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupView() {
		return "signup";
	}

}

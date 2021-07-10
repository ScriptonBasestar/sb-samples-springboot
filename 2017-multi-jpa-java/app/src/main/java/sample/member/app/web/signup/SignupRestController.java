package sample.member.app.web.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.member.core.transfer.EmptyResponseWrapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup/rest")
public class SignupRestController {

	@Autowired
	private SignupService signupService;

	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity checkUsername(@RequestParam String username) {
		if (!signupService.isExistsUsername(username)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity checkEmail(@RequestParam String email) {
		if (!signupService.isExistsEmail(email)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public EmptyResponseWrapper signupAction(@RequestBody @Valid SignupRequestDto requestDto) {
		signupService.signup(requestDto);
		return EmptyResponseWrapper.create().success(true);
	}

}

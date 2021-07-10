package hello.mvc.user;

import hello.domain.model.UserEntity;
import hello.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author archmagece
 * @date 2017-05-13
 */
@Slf4j
@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/user/join", method = RequestMethod.POST)
	public String join(@Valid UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.saveAndFlush(user);
		log.info("회원가입 성공 {}", user.getUsername());
		return "redirect:/hello";
	}

}

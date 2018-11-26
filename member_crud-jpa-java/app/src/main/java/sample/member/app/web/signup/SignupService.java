package sample.member.app.web.signup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.member.core.exception.BusinessException;
import sample.member.domain.model.UserEntity;
import sample.member.domain.repository.UserRepository;

@Slf4j
@Service
public class SignupService {

	@Autowired
	private UserRepository userRepository;

	public boolean isExistsUsername(String username) {
		log.trace("아이디(username) 중복 확인, username : {}", username);
		return userRepository.findByUsername(username).isPresent();
	}

	public boolean isExistsEmail(String email) {
		log.trace("이메일 중복 확인 - email : {}", email);
		return userRepository.findByEmail(email).isPresent();
	}

	@Transactional
	public void signup(SignupRequestDto requestDto) {
		log.trace("회원가입 액션 - username:{}, realname:{}, email:{}, nickname:{}, passwordSame:{}",
				requestDto.getUsername(), requestDto.getRealname(), requestDto.getEmail(), requestDto.getNickname(), requestDto.isPasswordSame());

		UserEntity userEntity = new UserEntity(requestDto.getUsername(), requestDto.getRealname(), requestDto.getEmail(), requestDto.getPassword());
		userEntity.setNickname(requestDto.getNickname());
		try {
			userRepository.save(userEntity);
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("계정정보가 중복됩니다. 가입된 계정이있는지 확인 해 주세요.");
		}
	}
}

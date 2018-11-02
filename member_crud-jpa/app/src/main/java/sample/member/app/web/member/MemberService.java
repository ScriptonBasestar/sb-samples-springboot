package sample.member.app.web.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sample.member.core.exception.DataNotFoundException;
import sample.member.domain.model.UserEntity;
import sample.member.domain.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class MemberService {

	@Autowired
	private UserRepository userRepository;

	public Page<UserEntity> list(Pageable pageable) {
		log.trace("회원 목록 가져오기 - page: {} size: {}", pageable.getPageSize(), pageable.getPageSize());
		return userRepository.findAll(pageable);
	}

	public UserEntity detail(String username) {
		log.trace("회원 상세 가져오기 - username: {}", username);
		Optional<UserEntity> optional = userRepository.findByUsername(username);
		if (!optional.isPresent()) {
			throw new DataNotFoundException("요청하신 데이터가 없습니다.");
		}
		return optional.get();
	}
}

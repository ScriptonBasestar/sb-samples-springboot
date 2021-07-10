package hello.security;

import com.google.common.collect.ImmutableList;
import hello.domain.model.UserEntity;
import hello.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author archmagece
 * @date 2017-05-12
 */
@Slf4j
public class DbUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			log.info("존재하지 않는 계정");
			throw new UsernameNotFoundException("존재하지 않은 계정");
		}
		return new SecurityUserDto(
				ImmutableList.<SimpleGrantedAuthority>builder().add(new SimpleGrantedAuthority("ROLE_USER")).build(),
				userEntity.getUsername(), userEntity.getPassword(),
				true, true, true, true
		);
	}
}

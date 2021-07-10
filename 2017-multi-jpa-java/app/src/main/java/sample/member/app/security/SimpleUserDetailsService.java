package sample.member.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sample.member.domain.model.UserEntity;
import sample.member.domain.repository.UserRepository;

import java.util.ArrayList;

public class SimpleUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public SimpleUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));

		return new SimpleUser(
				userEntity.getUsername(),
				new ArrayList<GrantedAuthority>() {{
					new SimpleGrantedAuthority("ROLE_USER");
				}},
				userEntity.getPassword()
		);
	}
}

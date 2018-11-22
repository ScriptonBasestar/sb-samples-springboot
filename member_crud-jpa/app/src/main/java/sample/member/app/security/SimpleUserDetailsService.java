package sample.member.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sample.member.domain.model.UserEntity;
import sample.member.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public SimpleUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> optional = userRepository.findByUsername(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException("사용자가 없습니다.");
		}
		UserEntity userEntity = optional.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new SimpleUser(
				userEntity.getUsername(),
				authorities,
				userEntity.getPassword()
		);
	}
}

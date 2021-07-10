package hello.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author archmagece
 * @date 2017-05-13
 */
@Getter
@AllArgsConstructor
public class SecurityUserDto implements UserDetails {
	private Collection<? extends GrantedAuthority> authorities;
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
}

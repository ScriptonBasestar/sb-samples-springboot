package sample.member.app.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class SimpleUser implements UserDetails {
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	public SimpleUser(String username, Collection<? extends GrantedAuthority> authorities, String password) {
		this.username = username;
		this.authorities = authorities;
		this.password = password;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
	}
}

package sample.member.app.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

//좀 더러운 해결법
//https://youtrack.jetbrains.com/issue/KT-6653
class SimpleUser(private val _username: String, private val _authorities: Collection<GrantedAuthority>, private val _password: String) : UserDetails {
	override fun getAuthorities(): Collection<GrantedAuthority> = _authorities
	override fun getUsername(): String = _username
	override fun getPassword(): String = _password

	override fun isAccountNonExpired(): Boolean = true
	override fun isEnabled(): Boolean = true
	override fun isCredentialsNonExpired(): Boolean = true
	override fun isAccountNonLocked(): Boolean = true
}

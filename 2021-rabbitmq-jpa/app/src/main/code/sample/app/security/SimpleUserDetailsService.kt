package sample.app.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import sample.domain.repository.UserRepository

class SimpleUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username).orElseThrow { UsernameNotFoundException("사용자가 없습니다") }
            .let {
                SimpleUser(
                    it.username,
                    listOf(SimpleGrantedAuthority("ROLE_USER")),
                    it.password
                )
            }
}

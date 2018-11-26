package sample.member.app.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import sample.member.app.security.Http403EntryPoint
import sample.member.app.security.SimpleUserDetailsService
import sample.member.domain.repository.UserRepository

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}

	override fun configure(web: WebSecurity) {
		web.ignoring()
				.antMatchers("/assets/**")
				.antMatchers("/vendor/**")
	}

//	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").anonymous()
				.antMatchers("/signup/**").anonymous()
				.anyRequest().hasRole("USER")
				.and()
				.formLogin().loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").loginPage("/login")
				.failureUrl("/login?error").defaultSuccessUrl("/member")
				.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
				.and()
				.exceptionHandling().authenticationEntryPoint(Http403EntryPoint()).accessDeniedPage("/login")
	}

//	@Throws(Exception::class)
	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder())
	}


	@Autowired
	private lateinit var userRepository: UserRepository
	@Bean
	override fun userDetailsService(): UserDetailsService = SimpleUserDetailsService(userRepository)

}

package sample.member.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sample.member.app.security.Http403EntryPoint;
import sample.member.app.security.SimpleUserDetailsService;
import sample.member.domain.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private final UserRepository userRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers("/favicon.ico")
				.antMatchers("/assets/**")
				.antMatchers("/vendor/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
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
				.exceptionHandling().authenticationEntryPoint(new Http403EntryPoint()).accessDeniedPage("/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new SimpleUserDetailsService(userRepository);
	}

}

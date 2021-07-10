package hello.config;

import hello.security.DbUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				//resource
				.antMatchers("/favicon.ico", "/vendor/**", "/static/**").permitAll()
				.antMatchers("/", "/home", "/join").permitAll()
				.antMatchers("/console/*").permitAll()
				.antMatchers("/user/join").anonymous()
				.anyRequest().authenticated()
				.and()

				.headers()
				.addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"))
				.frameOptions().disable()
				.and()

				.formLogin()
				.loginPage("/login")
				.permitAll()

				.and()

				.logout()
				.permitAll();
	}

	@Bean
	public DbUserDetailsService userDetailsService() {
		return new DbUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
		bean.setUserDetailsService(userDetailsService());
		bean.setPasswordEncoder(passwordEncoder());
		return bean;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.authenticationProvider(authenticationProvider());
//				메모리 로그인으로 적용시 사용.
//				.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER");
	}
}

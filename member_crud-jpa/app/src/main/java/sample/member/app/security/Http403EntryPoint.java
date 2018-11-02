package sample.member.app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class Http403EntryPoint implements AuthenticationEntryPoint {

	private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

	/**
	 * Always returns a 403 error code to the client.
	 */
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException ex) throws IOException, ServletException {
		if (log.isDebugEnabled()) {
			log.debug("Pre-authenticated entry point called. Rejecting access");
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		response.setStatus(HttpStatus.FORBIDDEN.value());
		if (authenticationTrustResolver.isAnonymous(authentication) || authentication == null) {
			request.getRequestDispatcher("/login").forward(request, response);
		} else {
			request.getRequestDispatcher("/error/403").forward(request, response);
		}
	}
}
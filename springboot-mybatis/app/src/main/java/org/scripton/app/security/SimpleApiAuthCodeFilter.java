package org.scripton.app.security;

import org.scripton.core.exception.SBAbortException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author archmagece
 * @since 2017-08-30
 */
public class SimpleApiAuthCodeFilter extends OncePerRequestFilter {

	private String serverAuthKey;
	private int serverAuthKeyLength = 0;
	private String serverAuthCode;

	@Override
	protected void initFilterBean() throws ServletException {
		serverAuthKey = getFilterConfig().getInitParameter("config.server.auth-key").trim() + " ";
		serverAuthKeyLength = serverAuthKey.length();
		serverAuthCode = getFilterConfig().getInitParameter("config.server.auth-code").trim();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (header != null && header.startsWith(serverAuthKey)) {
			String serverAuthCodeParam = header.substring(serverAuthKeyLength);
			if (serverAuthCodeParam.trim().contentEquals(serverAuthCode)) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		//헤더가 없거나 코드값이 틀리면 접근금지
		throw new SBAbortException("API auth failed. code is wrong or empty");
	}
}

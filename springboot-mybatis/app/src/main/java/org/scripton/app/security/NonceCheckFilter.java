package org.scripton.app.security;

import org.scripton.core.exception.SBAbortException;
import lombok.Setter;
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
public class NonceCheckFilter extends OncePerRequestFilter{

	@Setter
	private NonceCheckService nonceCheckService;

	private static final String NAME_OF_NONCE = "x-auth-nonce";
	private String nonceName;

	private static final String ERROR_MESSAGE = "중복 호출 - 이미 사용된 nonce 값입니다.";
	private String errorMessage;

	@Override
	protected void initFilterBean() throws ServletException {
		nonceName = getFilterConfig().getInitParameter("config.server.auth-nonce-name").trim();
		if(nonceName==null){
			nonceName = NAME_OF_NONCE;
		}
		errorMessage = getFilterConfig().getInitParameter("config.server.auth-nonce-error-message").trim();
		if(errorMessage==null){
			errorMessage = ERROR_MESSAGE;
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String nonce = request.getHeader(nonceName);

		if(nonceCheckService.isDuplicate(nonce)){
			//헤더가 없거나 코드값이 틀리면 접근금지
			throw new SBAbortException(errorMessage);
		}
		filterChain.doFilter(request, response);
	}
}

package sample.app.security

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationTrustResolverImpl
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class Http403EntryPoint : AuthenticationEntryPoint {

    companion object {
        val log = LoggerFactory.getLogger(Http403EntryPoint::class.java)!!
    }

    private val authenticationTrustResolver = AuthenticationTrustResolverImpl()

    /**
     * Always returns a 403 error code to the client.
     */
    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: AuthenticationException
    ) {
        if (log.isDebugEnabled) {
            log.debug("Pre-authenticated entry point called. Rejecting access")
        }

        val authentication = SecurityContextHolder.getContext().authentication
        response.status = HttpStatus.FORBIDDEN.value()
        if (authenticationTrustResolver.isAnonymous(authentication) || authentication == null) {
            request.getRequestDispatcher("/login").forward(request, response)
        } else {
            request.getRequestDispatcher("/error/403").forward(request, response)
        }
    }
}

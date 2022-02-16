package es.usj.androidapps.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val BEARER_PREFIX = "Bearer"

@Component
class JwtAuthTokenFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var tokenProvider: JwtProvider


    fun getJwt(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")
        return if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            authHeader.replace(BEARER_PREFIX, "").trim()
        } else null
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = getJwt(request)
        if (jwt != null && SecurityContextHolder.getContext().authentication == null) {
            val username = tokenProvider.getUserNameFromJwtToken(jwt)
            val user = tokenProvider.service.loadUserByUsername(username)
            if (tokenProvider.validateJwtToken(jwt, user)) {
                val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }

    fun whitelistedPaths(): List<String> {
        return listOf("/health-check", "/login")
    }


    @Throws(ServletException::class)
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        return whitelistedPaths().contains(path)
    }
}
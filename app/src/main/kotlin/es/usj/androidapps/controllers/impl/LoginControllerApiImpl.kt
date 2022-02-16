package es.usj.androidapps.controllers.impl

import es.usj.androidapps.controllers.GenreControllerApi
import es.usj.androidapps.controllers.LoginControllerApi
import es.usj.androidapps.model.dto.GenreDTO
import es.usj.androidapps.model.dto.LoginRequestDTO
import es.usj.androidapps.model.dto.LoginResponseDTO
import es.usj.androidapps.security.JwtProvider
import es.usj.androidapps.security.JwtUserDetailsService
import es.usj.androidapps.services.GenreServiceApi
import es.usj.androidapps.services.UserServiceApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class LoginControllerApiImpl : LoginControllerApi {

    @Autowired
    private lateinit var jwtUserDetailService: JwtUserDetailsService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtProvider: JwtProvider

    override fun login(body: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        authenticate(body.username, body.password)
        val userDetails = jwtUserDetailService.loadUserByUsername(body.username)
        val token = jwtProvider.generateJwtToken(userDetails.username, true)
        return ResponseEntity.ok(LoginResponseDTO(token))
    }

    @Throws(Exception::class)
    fun authenticate(username: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}
package es.usj.androidapps.security

import es.usj.androidapps.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var repository: UserRepository

    fun getPayload(username: String): Map<String, Any?> {
        val user = repository.findByUsername(username)
        return mapOf(
            "userId" to user?.id.toString()
        )
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository.findByUsername(username)
        return User(user?.username, user?.password, ArrayList())
    }
}
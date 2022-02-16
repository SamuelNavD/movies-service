package es.usj.androidapps.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.InvalidKeyException
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*


@Component
@PropertySource("classpath:application.properties")
class JwtProvider {

    companion object {
        const val USER_ID = "userId"
    }

    @Value("\${jwt.secret}")
    lateinit var secret: String

    @Value("\${jwt.token.expiration.time}")
    private var tokenExpirationTime: Long = 10 * 24 * 60 * 60 * 1000

    @Value("\${jwt.token.expiration.short.time}")
    private var tokenExpirationShortTime: Long = 30 * 60 * 1000

    @Autowired(required = false)
    lateinit var service: JwtUserDetailsService

    @Throws(InvalidKeyException::class)
    fun generateJwtToken(username: String, shortTimeToken: Boolean): String {
        val map = service.getPayload(username).toMutableMap()
        val created = Date().time
        val expiring = created + if (shortTimeToken) tokenExpirationShortTime else tokenExpirationTime
        return Jwts.builder()
            .setSubject(username)
            .addClaims(map)
            .setIssuedAt(Date(created))
            .setExpiration(Date(expiring))
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()), SignatureAlgorithm.HS512)
            .compact()
    }

    fun getUserNameFromJwtToken(token: String?): String {
        return Jwts.parserBuilder()
            .setSigningKey(secret.toByteArray()).build()
            .parseClaimsJws(token)
            .body.subject
    }

    fun getPropertyFromJwtToken(property: String, token: String?): String? {
        val value = Jwts.parserBuilder()
            .setSigningKey(secret.toByteArray()).build()
            .parseClaimsJws(token)
            .body[property]
        return value?.toString()
    }

    fun getUserIdFromJwtToken(token: String?): String? {
        return getPropertyFromJwtToken(USER_ID, token)
    }

    fun getExpirationDateFromToken(token: String): Date? {
        val value: Int = try {
            Jwts.parserBuilder()
                .setSigningKey(secret.toByteArray()).build()
                .parseClaimsJws(token)
                .body["exp"] as Int
        } catch (e: NullPointerException) {
            (Date().time / 1000).toInt()
        }
        return Date(value.toLong() * 1000)
    }

    fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration?.before(Date()) ?: false
    }

    fun validateJwtToken(token: String?, userDetails: UserDetails): Boolean {
        val username: String = getUserNameFromJwtToken(token)
        return username == userDetails.username && !isTokenExpired(token!!)
    }
}
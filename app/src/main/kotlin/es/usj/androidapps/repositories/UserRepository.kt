package es.usj.androidapps.repositories

import es.usj.androidapps.model.User
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepositoryImplementation<User, UUID> {
    fun findByUsername(username: String): User?
}
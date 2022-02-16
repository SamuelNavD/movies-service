package es.usj.androidapps.repositories

import es.usj.androidapps.model.Genre
import es.usj.androidapps.model.User
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import java.util.UUID

interface UserRepository : JpaRepositoryImplementation<User, UUID> {
    fun findByUsername(username: String) : User?
}
package es.usj.androidapps.repositories

import es.usj.androidapps.model.AppUser
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepositoryImplementation<AppUser, Long> {
    fun findByUsername(username: String): AppUser?
}
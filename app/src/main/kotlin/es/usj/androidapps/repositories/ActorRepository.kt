package es.usj.androidapps.repositories

import es.usj.androidapps.model.Actor
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ActorRepository : JpaRepositoryImplementation<Actor, UUID> {
}
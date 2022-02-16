package es.usj.androidapps.repositories

import es.usj.androidapps.model.Actor
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import java.util.UUID

interface ActorRepository : JpaRepositoryImplementation<Actor, UUID> {
}
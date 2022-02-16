package es.usj.androidapps.repositories

import es.usj.androidapps.model.Actor
import es.usj.androidapps.model.Genre
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import java.util.UUID

interface GenreRepository : JpaRepositoryImplementation<Genre, UUID> {
}
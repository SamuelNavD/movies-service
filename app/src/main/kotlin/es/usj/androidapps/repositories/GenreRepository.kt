package es.usj.androidapps.repositories

import es.usj.androidapps.model.Genre
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GenreRepository : JpaRepositoryImplementation<Genre, UUID> {
}
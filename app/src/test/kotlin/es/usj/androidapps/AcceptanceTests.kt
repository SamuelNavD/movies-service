package es.usj.androidapps

import com.fasterxml.jackson.module.kotlin.jsonMapper
import es.usj.androidapps.infrastructure.BaseTest
import es.usj.androidapps.infrastructure.TestProperties
import es.usj.androidapps.model.dto.ActorDTO
import es.usj.androidapps.model.dto.CountDTO
import es.usj.androidapps.model.dto.GenreDTO
import es.usj.androidapps.model.dto.MovieDTO
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

const val ACTOR_PATH = "/actors"
const val GENRE_PATH = "/genres"
const val MOVIE_PATH = "/movies"

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AcceptanceTests : BaseTest(TestProperties.local()) {

    @Test
    fun `add actor without id returns actor with maximum id`() {
        val items = ACTOR_PATH.GET<ActorDTO>(jsonMapper())
        val size = items.count()
        val item = ActorDTO(0, "Juanjo")
        val returned = ACTOR_PATH.POST<ActorDTO>(item)
        assert(returned.id > size)
        assert(ACTOR_PATH.GET<ActorDTO>(jsonMapper()).count() > size)
    }

    @Test
    fun `edit actor returns actor properly`() {
        val item = ActorDTO(0, "Juanjo${Math.random()}")
        val created = ACTOR_PATH.POST<ActorDTO>(item)
        created.name = "Juanjo${Math.random()}"
        val size = ACTOR_PATH.PUT<CountDTO>(created)
        assert(size.count == 1)
        val edited = "$ACTOR_PATH/${created.id}".GET<ActorDTO>()
        assert(edited.id == created.id)
        assert(edited.name == created.name)
    }

    @Test
    fun `delete an actor by id works`() {
        val item = ActorDTO(0, "Juanjo${Math.random()}")
        val created = ACTOR_PATH.POST<ActorDTO>(item)
        val found = "$ACTOR_PATH/${created.id}".GET<ActorDTO>()
        "$ACTOR_PATH/${found.id}".DELETE<ActorDTO>()
        assertThrows<Exception> { "$ACTOR_PATH/${created.id}".GET<ActorDTO>() }
    }

    @Test
    fun `list actors works`() {
        val found = ACTOR_PATH.GET<ActorDTO>(jsonMapper())
        assert(found.isNotEmpty())
    }

    @Test
    fun `add genre without id returns genre with maximum id`() {
        val items = GENRE_PATH.GET<GenreDTO>(jsonMapper())
        val size = items.count()
        val item =  GenreDTO(0, "Action${Math.random()}")
        val returned = GENRE_PATH.POST<GenreDTO>(item)
        assert(returned.id > size)
        assert(GENRE_PATH.GET<GenreDTO>(jsonMapper()).count() > size)
    }

    @Test
    fun `edit genre returns genre properly`() {
        val item = GenreDTO(0, "Action${Math.random()}")
        val created = GENRE_PATH.POST<GenreDTO>(item)
        created.name = "Juanjo${Math.random()}"
        val size = GENRE_PATH.PUT<CountDTO>(created)
        assert(size.count == 1)
        val edited = "$GENRE_PATH/${created.id}".GET<GenreDTO>()
        assert(edited.id == created.id)
        assert(edited.name == created.name)
    }

    @Test
    fun `delete a genre by id works`() {
        val item = GenreDTO(0, "Action${Math.random()}")
        val created = GENRE_PATH.POST<GenreDTO>(item)
        val found = "$GENRE_PATH/${created.id}".GET<GenreDTO>()
        "$GENRE_PATH/${found.id}".DELETE<GenreDTO>()
        assertThrows<Exception> { "$GENRE_PATH/${created.id}".GET<GenreDTO>() }
    }

    @Test
    fun `list genre works`() {
        val found = GENRE_PATH.GET<GenreDTO>(jsonMapper())
        assert(found.isNotEmpty())
    }

    fun createMovieDTO() : MovieDTO {
        return MovieDTO(0L,
            "Title${Math.random()}",
            "Description${Math.random()}",
            "Description${Math.random()}",
            (Math.random()*2022).toInt(),
            (Math.random()*180).toInt(),
            Math.random(),
            (Math.random() * 10000000).toLong(),
            Math.random(),
            listOf(1,2,3),
            listOf(4,2)
        )
    }

    @Test
    fun `add movie without id returns movie with maximum id`() {
        val items = MOVIE_PATH.GET<MovieDTO>(jsonMapper())
        val size = items.count()
        val item = createMovieDTO()
        val returned = MOVIE_PATH.POST<MovieDTO>(item)
        assert(returned.id > size)
        assert(MOVIE_PATH.GET<MovieDTO>(jsonMapper()).count() > size)
    }

    @Test
    fun `edit movie returns movie properly`() {
        val item = createMovieDTO()
        val created = MOVIE_PATH.POST<MovieDTO>(item)
        created.title = "Matrix${Math.random()}"
        val size = MOVIE_PATH.PUT<CountDTO>(created)
        assert(size.count == 1)
        val edited = "$MOVIE_PATH/${created.id}".GET<MovieDTO>()
        assert(edited.id == created.id)
        assert(edited.title == created.title)
    }


    @Test
    fun `delete a movie by id works`() {
        val item = createMovieDTO()
        val created = MOVIE_PATH.POST<MovieDTO>(item)
        val found = "$MOVIE_PATH/${created.id}".GET<MovieDTO>()
        "$MOVIE_PATH/${found.id}".DELETE<MovieDTO>()
        assertThrows<Exception> { "$MOVIE_PATH/${created.id}".GET<MovieDTO>() }
    }

    @Test
    fun `list movie works`() {
        val found = MOVIE_PATH.GET<MovieDTO>(jsonMapper())
        assert(found.isNotEmpty())
    }
}
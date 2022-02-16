package es.usj.androidapps.model

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.util.*
import javax.persistence.*

@Entity
class Movie(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(unique = true)
    val title: String,
    @Column(columnDefinition = "TEXT")
    val description: String,
    @Column
    val director: String,
    @Column
    val year: Int,
    @Column
    val runtime: Int,
    @Column
    val rating: Double,
    @Column
    val votes: Long,
    @Column
    val revenue: Double,
    @ManyToMany(cascade = [CascadeType.ALL])
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name = "actor_movie",
        joinColumns = [JoinColumn(name = "actor_id")],
        inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    val actors: MutableList<Actor> = mutableListOf(),
    @ManyToMany(cascade = [CascadeType.ALL])
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name = "genre_movie",
        joinColumns = [JoinColumn(name = "genre_id")],
        inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    val genres: MutableList<Genre> = mutableListOf()
) {
    fun addAllGenres(genres: List<Genre>) {
        genres.forEach { addGenre(it) }
    }

    fun addGenre(genre: Genre) {
        genre.movies.add(this)
        this.genres.add(genre)
    }

    fun addAllActors(actors: List<Actor>) {
        actors.forEach { addActor(it) }
    }

    fun addActor(actor: Actor) {
        actor.movies.add(this)
        this.actors.add(actor)
    }
}
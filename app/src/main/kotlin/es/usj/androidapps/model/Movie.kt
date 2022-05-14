package es.usj.androidapps.model

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.*

@Entity
@Table(name = "movies")
class Movie(
    @Id
    var id: Long,
    @Column(length = 250)
    var title: String,
    @Column(columnDefinition = "TEXT")
    var description: String,
    @Column
    var director: String,
    @Column(name = "release_year")
    var year: Int,
    @Column
    var runtime: Int,
    @Column
    var rating: Double,
    @Column
    var votes: Long,
    @Column
    var revenue: Double,
    @ManyToMany(cascade = [CascadeType.ALL])
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name = "actors_to_movies",
        joinColumns = [JoinColumn(name = "id_actor")],
        inverseJoinColumns = [JoinColumn(name = "id_movie")]
    )
    var actors: MutableList<Actor> = mutableListOf(),
    @ManyToMany(cascade = [CascadeType.ALL])
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name = "genres_to_movies",
        joinColumns = [JoinColumn(name = "id_genre")],
        inverseJoinColumns = [JoinColumn(name = "id_movie")]
    )
    var genres: MutableList<Genre> = mutableListOf()
) {
    constructor() : this(0, "", "", "", 0, 0, 0.0, 0, 0.0)

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
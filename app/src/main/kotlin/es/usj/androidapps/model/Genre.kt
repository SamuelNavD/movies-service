package es.usj.androidapps.model

import java.util.*
import javax.persistence.*

@Entity
class Genre(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(unique = true)
    val name: String,
    @ManyToMany(mappedBy = "genres")
    val movies: MutableList<Movie> = mutableListOf()
)
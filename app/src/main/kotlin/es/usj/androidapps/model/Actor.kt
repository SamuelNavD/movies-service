package es.usj.androidapps.model

import java.util.*
import javax.persistence.*

@Entity
class Actor(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(unique = true)
    val name: String,
    @JoinColumn(nullable = true)
    val movies: MutableList<Movie> = mutableListOf()
)
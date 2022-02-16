package es.usj.androidapps.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany

@Entity
class Actor(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(unique = true)
    val name: String,
    @ManyToMany(mappedBy = "actors")
    val movies: MutableList<Movie> = mutableListOf()
)
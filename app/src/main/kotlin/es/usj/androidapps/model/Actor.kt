package es.usj.androidapps.model

import javax.persistence.*

@Entity
@Table(name = "actors")
class Actor(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(unique = true)
    val name: String,
    @ManyToMany(mappedBy = "actors")
    val movies: MutableList<Movie> = mutableListOf()
) {
    constructor() : this(0, "")
}
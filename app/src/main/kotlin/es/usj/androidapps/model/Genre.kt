package es.usj.androidapps.model

import javax.persistence.*

@Entity
@Table(name = "genres")
class Genre(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(unique = true)
    val name: String,
    @ManyToMany(mappedBy = "genres")
    val movies: MutableList<Movie> = mutableListOf()
) {
    constructor() : this(0, "")
}
package es.usj.androidapps.model.dto

data class MovieDTO(
    val id: Long,
    var title: String,
    val description: String,
    val director: String,
    val year: Int,
    val runtime: Int,
    val rating: Double,
    val votes: Long,
    val revenue: Double,
    val actors: List<Long> = emptyList(),
    val genres: List<Long> = emptyList()
) {
    init {
        title = title.trim()
    }

    constructor(
        id: Long,
        title: String,
        description: String,
        director: String,
        year: Int,
        runtime: Int,
        rating: Int,
        votes: Long,
        revenue: Double,
        actors: List<Long> = emptyList(),
        genres: List<Long> = emptyList()
    ) : this(id, title, description, director, year, runtime, rating.toDouble(), votes, revenue, actors, genres)

    constructor(
        id: Long,
        title: String,
        description: String,
        director: String,
        year: Int,
        runtime: Int,
        rating: Int,
        votes: Long,
        revenue: Int,
        actors: List<Long> = emptyList(),
        genres: List<Long> = emptyList()
    ) : this(
        id,
        title,
        description,
        director,
        year,
        runtime,
        rating.toDouble(),
        votes,
        revenue.toDouble(),
        actors,
        genres
    )

    constructor(
        id: Long,
        title: String,
        description: String,
        director: String,
        year: Int,
        runtime: Int,
        rating: Double,
        votes: Long,
        revenue: Int,
        actors: List<Long> = emptyList(),
        genres: List<Long> = emptyList()
    ) : this(id, title, description, director, year, runtime, rating, votes, revenue.toDouble(), actors, genres)
}
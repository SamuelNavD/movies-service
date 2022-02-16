package es.usj.androidapps.model.dto

import java.util.*

data class MovieDTO(
    val id: UUID,
    val title: String,
    val description: String,
    val director: String,
    val runtime: Int,
    val year: Int,
    val rating: Double,
    val votes: Long,
    val revenue: Double,
    val actors: List<ActorDTO>,
    val genres: List<GenreDTO>
)
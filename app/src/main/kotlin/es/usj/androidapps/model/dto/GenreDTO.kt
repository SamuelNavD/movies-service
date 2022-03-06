package es.usj.androidapps.model.dto

data class GenreDTO(val id: Long, var name: String) {
    init {
        name = name.trim()
    }
}
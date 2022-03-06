package es.usj.androidapps.model.dto

data class ActorDTO(val id: Long, var name: String) {
    init {
        name = name.trim()
    }
}
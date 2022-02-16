package es.usj.androidapps.controllers.impl

import es.usj.androidapps.controllers.ActorControllerApi
import es.usj.androidapps.model.dto.ActorDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class ActorControllerApiImpl : ActorControllerApi {
    override fun createActor(body: ActorDTO): ResponseEntity<ActorDTO> {
        TODO("Not yet implemented")
    }

    override fun updateActor(body: ActorDTO): ResponseEntity<Int> {
        TODO("Not yet implemented")
    }

    override fun deleteActor(id: UUID): ResponseEntity<ActorDTO> {
        TODO("Not yet implemented")
    }

    override fun getActorById(id: UUID): ResponseEntity<ActorDTO> {
        TODO("Not yet implemented")
    }

    override fun getActors(limit: Int?, offset: Long?): ResponseEntity<List<ActorDTO>> {
        TODO("Not yet implemented")
    }
}
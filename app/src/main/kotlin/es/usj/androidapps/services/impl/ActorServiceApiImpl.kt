package es.usj.androidapps.services.impl

import es.usj.androidapps.model.dto.ActorDTO
import es.usj.androidapps.repositories.ActorRepository
import es.usj.androidapps.services.ActorServiceApi
import es.usj.androidapps.utils.DataConverter
import es.usj.androidapps.utils.OffsetBasedPageRequest
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class ActorServiceApiImpl : ActorServiceApi {

    @Autowired
    lateinit var actorRepository: ActorRepository

    override fun list(limit: Int?, offset: Long?): List<ActorDTO> {
        if(limit != null && offset != null) {
            val pageable = OffsetBasedPageRequest(
                offset,
                limit
            )
            return actorRepository.findAll(pageable).toList().map { DataConverter.actorToDTO(it) }
        }
        return actorRepository.findAll().map { DataConverter.actorToDTO(it) }
    }

    override fun find(id: UUID): ActorDTO? {
        return DataConverter.actorToDTO(actorRepository.findById(id).get())
    }

    override fun delete(id: UUID): ActorDTO {
        val actor = find(id)
        if(actor != null) {
            actorRepository.deleteById(id)
            return actor
        } else {
            throw Exception("Actor not found")
        }
    }

    override fun save(element: ActorDTO): ActorDTO {
        val item = DataConverter.actorFromDTO(element)
        return DataConverter.actorToDTO(actorRepository.save(item))
    }
}
package es.usj.androidapps.services.impl

import es.usj.androidapps.model.dto.GenreDTO
import es.usj.androidapps.repositories.GenreRepository
import es.usj.androidapps.services.GenreServiceApi
import es.usj.androidapps.utils.DataConverter
import es.usj.androidapps.utils.OffsetBasedPageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GenreServiceApiImpl : GenreServiceApi {
    @Autowired
    lateinit var genreRepository: GenreRepository

    override fun list(limit: Int?, offset: Long?): List<GenreDTO> {
        if(limit != null && offset != null) {
            val pageable = OffsetBasedPageRequest(
                offset,
                limit
            )
            return genreRepository.findAll(pageable).toList().map { DataConverter.genreToDTO(it) }
        }
        return genreRepository.findAll().map { DataConverter.genreToDTO(it) }
    }

    override fun find(id: UUID): GenreDTO? {
        return DataConverter.genreToDTO(genreRepository.findById(id).get())
    }

    override fun delete(id: UUID): GenreDTO {
        val genre = find(id)
        if(genre != null) {
            genreRepository.deleteById(id)
            return genre
        } else {
            throw Exception("Genre not found")
        }
    }

    override fun save(element: GenreDTO): GenreDTO {
        val item = DataConverter.genreFromDTO(element)
        return DataConverter.genreToDTO(genreRepository.save(item))
    }
}
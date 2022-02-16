package es.usj.androidapps.services.impl

import es.usj.androidapps.model.dto.MovieDTO
import es.usj.androidapps.repositories.MovieRepository
import es.usj.androidapps.services.MovieServiceApi
import es.usj.androidapps.utils.DataConverter
import es.usj.androidapps.utils.OffsetBasedPageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieServiceApiImpl : MovieServiceApi {
    @Autowired
    lateinit var movieRepository: MovieRepository

    override fun list(limit: Int?, offset: Long?): List<MovieDTO> {
        if (limit != null && offset != null) {
            val pageable = OffsetBasedPageRequest(
                offset,
                limit
            )
            return movieRepository.findAll(pageable).toList().map { DataConverter.movieToDTO(it) }
        }
        return movieRepository.findAll().map { DataConverter.movieToDTO(it) }
    }

    override fun find(id: UUID): MovieDTO? {
        return DataConverter.movieToDTO(movieRepository.findById(id).get())
    }

    override fun delete(id: UUID): MovieDTO {
        val movie = find(id)
        if (movie != null) {
            movieRepository.deleteById(id)
            return movie
        } else {
            throw Exception("Movie not found")
        }
    }

    override fun save(element: MovieDTO): MovieDTO {
        val item = DataConverter.movieFromDTO(element)
        return DataConverter.movieToDTO(movieRepository.save(item))
    }
}
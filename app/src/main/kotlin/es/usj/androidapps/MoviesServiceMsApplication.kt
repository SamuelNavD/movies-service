package es.usj.androidapps

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import es.usj.androidapps.model.Actor
import es.usj.androidapps.model.Genre
import es.usj.androidapps.model.Movie
import es.usj.androidapps.repositories.ActorRepository
import es.usj.androidapps.repositories.GenreRepository
import es.usj.androidapps.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@EntityScan(basePackages = ["es.usj.androidapps"])
@SpringBootApplication
class MoviesServiceMsApplication {

    @Autowired
    lateinit var mservice: MovieRepository
    @Autowired
    lateinit var gservice: GenreRepository
    @Autowired
    lateinit var aservice: ActorRepository
    @PostConstruct
    fun init() {
        val reader = csvReader {
            delimiter = '|'
        }

        val genres = mutableMapOf<String, Genre>()
        val actors = mutableMapOf<String, Actor>()
        val movies = mutableMapOf<Long, Movie>()
        val file = System.getProperty("user.dir") + "/app/src/main/resources/data.csv"
        var count = 0
        reader.open(file) {
            readAllAsSequence(12).forEach { row ->
                if (count != 0) {
                    val genresInMovie = cleanRow(row[2], "").split(',')
                    val actorsInMovie = cleanRow(row[5], "").split(',')
                    val gim = mutableListOf<Genre>()
                    val aim = mutableListOf<Actor>()
                    genresInMovie.forEach {
                        if (genres[it] == null) {
                            genres[it] = Genre(genres.size.toLong() + 1, it)
                            genres[it]?.let { it1 -> gservice.save(it1) }
                        }
                        gim.add(genres[it]!!)
                    }
                    actorsInMovie.forEach {
                        if (actors[it] == null) {
                            actors[it] = Actor(actors.size.toLong() + 1, it)
                            actors[it]?.let { it1 -> aservice.save(it1) }
                        }
                        aim.add(actors[it]!!)
                    }
                    val movie = Movie()
                    movie.id = cleanRow(row[0], "0").toLong()
                    movie.title = cleanRow(row[1], "No title")
                    movie.description = cleanRow(row[3],"No description")
                    movie.director = cleanRow(row[4],"No director")
                    movie.year = cleanRow(row[6], "0").toInt()
                    movie.runtime = cleanRow(row[7], "0").toInt()
                    movie.rating = cleanRow(row[8], "0.0").toDouble()
                    movie.votes = cleanRow(row[9], "0").toLong()
                    movie.revenue = cleanRow(row[10], "0.0").toDouble()
                    movie.addAllGenres(gim)
                    movie.addAllActors(aim)
                    movies[movie.id] = movie
                    mservice.save(movie)
                }
                count++
            }
        }
    }
}

fun main(args: Array<String>) {

    runApplication<MoviesServiceMsApplication>(*args)
}

fun cleanRow(row: String, default: String) : String {
    var content = row.replace(";,","")
    content = content.replace(";","")
    return content.ifEmpty { default }
}
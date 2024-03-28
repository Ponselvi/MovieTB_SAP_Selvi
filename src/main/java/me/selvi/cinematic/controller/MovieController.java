package me.selvi.cinematic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.selvi.cinematic.model.Movie;
import me.selvi.cinematic.model.Screening;
import me.selvi.cinematic.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "Movies", description = "Returns movie related APIS")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    @ApiOperation(value = "Get All Movies", notes = "Returns all movies")
    public ResponseEntity<?> getAllMovies(@RequestParam Map<String, String> dates) {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @GetMapping("/movies/{movie_id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long movie_id) {
        Movie movie = movieService.getMovieById(movie_id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @GetMapping("/movies/{movie_id}/screenings")
    public ResponseEntity<?> getAllScreeningByMovieId(@PathVariable Long movie_id) {
        List<Screening> screenings = movieService.getAllScreeningByMovieId(movie_id);
        return ResponseEntity.status(HttpStatus.OK).body(screenings);
    }

    @DeleteMapping("/movies/{movie_id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long movie_id) {
        movieService.deleteMovie(movie_id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/movies", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Movie addMovie(@RequestBody Movie movie) {

        return movieService.pushMovie(movie);
    }
}


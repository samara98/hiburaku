package com.samara98.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //    create
    @PostMapping
    public Movie createMovie(@RequestBody Movie newMovie) {
        return movieService.createMovie(newMovie);
    }

//    read all
    @GetMapping
    public List<Movie> getMovieList() {
        return movieService.getMovieList();
    }

//    read one
    @GetMapping(path = "{movie_id}")
    public Movie getMovie(@PathVariable("movie_id") Long movieId) {
        return movieService.getMovie(movieId);
    }

//    update
    @PatchMapping(path = "{movie_id}")
    public Movie updatMovie(@RequestBody Movie newMovie, @PathVariable("movie_id") Long movieId) {
        return movieService.updateMovie(newMovie, movieId);
    }

//    delete
    @DeleteMapping(path = "{movie_id}")
    public void deleteMovie(@PathVariable("movie_id") Long movieId) {
        movieService.deleteMovie(movieId);
    }
}

package com.samara98.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getMovieList() {
        return movieRepository.findAll();
    }

    public Movie getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalStateException("movie with id " + movieId + " does not exist"));
        return movie;
    }

    @Transactional
    public Movie updateMovie(Movie newMovie, Long movieId) {
        boolean exist = movieRepository.existsById(movieId);
        if (!exist) {
            throw new IllegalStateException("movie with id " + movieId + " does not exist");
        }

        Movie updatedMovie = movieRepository.findById(movieId).map(movie -> {
            movie.setTitle(newMovie.getTitle());
            movie.setOverview(newMovie.getOverview());
            movie.setPosterPath(newMovie.getPosterPath());
            movie.setPopularity(newMovie.getPopularity());
            return movieRepository.save(movie);
        }).orElseGet(() -> {
            newMovie.setId(movieId);
            return movieRepository.save(newMovie);
        });

        return updatedMovie;
    }

    public void deleteMovie(Long movieId) {
        boolean exist = movieRepository.existsById(movieId);
        if (!exist) {
            throw new IllegalStateException("movie with id " + movieId + " does not exist");
        }

        movieRepository.deleteById(movieId);
    }
}

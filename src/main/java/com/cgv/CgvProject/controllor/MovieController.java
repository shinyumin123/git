package com.cgv.CgvProject.controllor;

import com.cgv.CgvProject.DTO.MovieDTO;
import com.cgv.CgvProject.domain.Movie;
import com.cgv.CgvProject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;


    @GetMapping("/movie/{movieId}")
    public MovieDTO.MovieResponse getMovie(@PathVariable Long movieId) throws IOException {
        Movie movie = movieService.findByMovieId(movieId);
        MovieDTO.MovieResponse response = new MovieDTO.MovieResponse(movie.id, movie.movieId, movie.title, movie.overview, movie.voteAverage, movie.releaseDate, movie.adult);
        return response;
    }


    @GetMapping("/movie")
    public ResponseEntity<String> getAllMovies() throws IOException {
        String movies = movieService.getNowPlayingMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movie/TitleAndAdult/{movieId}")
    public MovieDTO.MovieTitleAndAdult findTitleAndAdult(@PathVariable Long movieId) throws IOException
    {
        Movie movie = movieService.findByMovieId(movieId);
        MovieDTO.MovieTitleAndAdult response = new MovieDTO.MovieTitleAndAdult(movie.title, movie.adult);
        return response;
    }


}

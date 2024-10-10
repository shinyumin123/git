package com.cgv.CgvProject.repositroty;

import com.cgv.CgvProject.domain.Movie;

public interface MovieRepository {

    Movie findByMovieId(Long movieId);

    Movie save(Movie movie);

    Movie findTitleAndAdult(String title, boolean adult);
}

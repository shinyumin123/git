package com.cgv.CgvProject.DTO;

import com.cgv.CgvProject.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

public class MovieDTO {

    @Data
    public static class MovieResponse {
        private Long id;
        private Long movieId;
        private String title;
        private String overview;
        private double voteAverage;
        private String release_date;
        private boolean adult;

        public MovieResponse(Long id, Long movieId, String title, String overview, double voteAverage, String release_date, boolean adult) {
            this.id = id;
            this.movieId = movieId;
            this.title = title;
            this.overview = overview;
            this.voteAverage = voteAverage;
            this.release_date = release_date;
            this.adult = adult;
        }
    }

    @Data
    public static class MovieTitleAndAdult {
        private String title;
        private boolean adult;

        public MovieTitleAndAdult(String title, boolean adult) {
            this.title = title;
            this.adult = adult;
        }

    }
}

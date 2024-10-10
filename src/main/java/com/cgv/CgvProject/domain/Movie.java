package com.cgv.CgvProject.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Movie {

    @Id
    @GeneratedValue
    public long id;

    public long movieId;
    public String title;
    @Column(columnDefinition = "TEXT")
    public String overview;
    public String releaseDate;
    public double voteAverage;
    public boolean adult;


    public Movie(Long movieId, String title, String overview, String releaseDate, double voteAverage, boolean adult) {
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.adult = adult;
    }
}

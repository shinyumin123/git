package com.cgv.CgvProject.service;

import com.cgv.CgvProject.domain.Movie;
import com.cgv.CgvProject.repositroty.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final OkHttpClient client = new OkHttpClient();

    public Movie findByMovieId(Long movieId){
        return movieRepository.findByMovieId(movieId);
    }

    public Movie findTitleAndAdult(String title, boolean adult){
        return movieRepository.findTitleAndAdult(title, adult);
    }

    public String getNowPlayingMovies() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?language=ko&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNzI0YzNlNTFlMGU1NTVhODdjZjAxODE5NWQ4NjM5MSIsIm5iZiI6MTcyNjkyMzUyOC44OTY1MjgsInN1YiI6IjY2ZTJhMmE4MDAwMDAwMDAwMGI5MTY0ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.69dhHanptQ8yW4GqIDqjD9oQnKOXGVR_BwRXNPBQEws")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(response.body().string());

                for (JsonNode movieNode : node.get("results")) {
                    Long movieId = movieNode.get("id").asLong();
                    String title = movieNode.get("title").asText();
                    String releaseDate = movieNode.get("release_date").asText();
                    String overview = movieNode.get("overview").asText();
                    Double voteAverage = movieNode.get("vote_average").asDouble();
                    Boolean adult = movieNode.get("adult").asBoolean();

                    Movie movie = movieRepository.findByMovieId(movieId);

                    if (movie == null) {
                        movie = new Movie(movieId, title, overview, releaseDate, voteAverage, adult);
                        movieRepository.save(movie);
                    }
                }
                return "영화 저장 성공";
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }
}

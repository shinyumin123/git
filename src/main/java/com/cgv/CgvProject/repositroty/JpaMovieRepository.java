package com.cgv.CgvProject.repositroty;

import com.cgv.CgvProject.domain.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaMovieRepository implements MovieRepository {
    private final EntityManager em;

    public Movie findByMovieId(Long movieId){
        List<Movie> result = em.createQuery("Select m from Movie m where m.movieId =:movieId",Movie.class)
                .setParameter("movieId",movieId)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    public Movie save(Movie movie){
        em.persist(movie);
        return movie;
    }

    public Movie findTitleAndAdult(String title, boolean adult){
        List<Movie> result = em.createQuery("select m from Movie m where m.title =:title AND m.adult =: adult",Movie.class)
                .setParameter("title",title.trim())
                .setParameter("adult",adult)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
}

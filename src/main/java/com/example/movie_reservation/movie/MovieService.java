package com.example.movie_reservation.movie;

import com.example.movie_reservation.movie.dto.MovieResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponseDto> getMovieList(){
        return movieRepository
                .getActiveMovieList()
                .stream()
                .map(MovieResponseDto::from).toList();
    }
}

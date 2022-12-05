package com.example.movie_reservation.movie;

import com.example.movie_reservation.movie.dto.MovieRequestDto;
import com.example.movie_reservation.movie.dto.MovieResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(value = "${apiPrefix}")
@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/movie/{movieId}")
    public MovieResponseDto getMovieById(@PathVariable(value = "movieId") UUID movieId){
        return null;
    }

    @GetMapping(value = "/movies")
    public List<MovieResponseDto> getMovies(){
        return null;
    }

    @PutMapping(value = "/movie")
    public MovieResponseDto createMovie(@RequestBody MovieRequestDto movieRequestDto){
        return null;
    }

}

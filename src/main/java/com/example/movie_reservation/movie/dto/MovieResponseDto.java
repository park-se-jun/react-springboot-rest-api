package com.example.movie_reservation.movie.dto;

import com.example.movie_reservation.movie.model.Movie;

import java.time.LocalDate;
import java.util.UUID;

public record MovieResponseDto(UUID movieId, String title, LocalDate releaseDate,String posterUrl){
    public static MovieResponseDto from(Movie movie){
        return new MovieResponseDto(movie.getId(), movie.getTitle(), movie.getReleaseDate(),movie.getPosterUrl());
    }
}

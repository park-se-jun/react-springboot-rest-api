package com.example.movie_reservation.theater.dto;

import com.example.movie_reservation.theater.model.Theater;

import java.util.UUID;

public record TheaterGetResponseDto(UUID theaterId,String theaterName) {
    public static TheaterGetResponseDto from(Theater theater){
        return new TheaterGetResponseDto(theater.getTheaterId(), theater.getTheaterName());
    }
}

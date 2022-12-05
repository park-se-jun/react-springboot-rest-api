package com.example.movie_reservation.cinema;

import java.util.Map;

public record SeatsResponseDto(Map<String, Boolean[]> seats) {
    public static SeatsResponseDto from(Map<String, Boolean[]> seats) {
        return new SeatsResponseDto(seats);
    }
}

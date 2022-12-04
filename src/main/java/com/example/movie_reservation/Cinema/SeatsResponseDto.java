package com.example.movie_reservation.Cinema;

import java.util.Map;

public record SeatsResponseDto(Map<String, Boolean[]> seats) {
    public static SeatsResponseDto from(Map<String, Boolean[]> seats) {
        return new SeatsResponseDto(seats);
    }
}

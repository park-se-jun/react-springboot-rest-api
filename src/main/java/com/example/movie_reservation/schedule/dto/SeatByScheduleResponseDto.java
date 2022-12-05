package com.example.movie_reservation.schedule.dto;

import java.util.Map;

public record SeatByScheduleResponseDto(Map<String, Boolean[]> seats) {
    public static SeatByScheduleResponseDto from(Map<String, Boolean[]> seats) {
        return new SeatByScheduleResponseDto(seats);
    }
}

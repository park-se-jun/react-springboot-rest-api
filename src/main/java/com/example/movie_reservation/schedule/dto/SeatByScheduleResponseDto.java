package com.example.movie_reservation.schedule.dto;

import java.util.Map;

public record SeatByScheduleResponseDto(Map<String,Boolean[]> seats) {
}

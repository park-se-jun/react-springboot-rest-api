package com.example.movie_reservation.schedule.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateScheduleRequestDto(
        LocalDateTime startTime,
        UUID movieId,
        UUID screenId
) {
}

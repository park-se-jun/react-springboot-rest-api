package com.example.movie_reservation.schedule;

import java.time.LocalDateTime;
import java.util.UUID;

public class Shcedule {
    private final UUID scheduleId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final UUID movieId;
    private final UUID cinemaTheaterId;
    public Shcedule(UUID scheduleId, LocalDateTime startTime, LocalDateTime endTime, UUID movieId, UUID cinemaTheaterId) {
        this.scheduleId = scheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieId = movieId;
        this.cinemaTheaterId = cinemaTheaterId;
    }
}

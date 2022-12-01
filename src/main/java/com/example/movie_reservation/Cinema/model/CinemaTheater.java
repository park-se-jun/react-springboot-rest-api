package com.example.movie_reservation.Cinema.model;

import java.util.UUID;

public class CinemaTheater {
    private final UUID cinemaTheaterId;
    private final UUID cinemaId;
    private final UUID theaterId;

    public CinemaTheater(UUID cinemaTheaterId, UUID cinemaId, UUID theaterId) {
        this.cinemaTheaterId = cinemaTheaterId;
        this.cinemaId = cinemaId;
        this.theaterId = theaterId;
    }
}

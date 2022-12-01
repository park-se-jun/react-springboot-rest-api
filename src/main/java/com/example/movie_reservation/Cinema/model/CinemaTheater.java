package com.example.movie_reservation.Cinema.model;

import java.util.UUID;

public class CinemaTheater {
    private final UUID id;
    private final UUID cinemaId;
    private final UUID theaterId;

    public CinemaTheater(UUID id, UUID cinemaId, UUID theaterId) {
        this.id = id;
        this.cinemaId = cinemaId;
        this.theaterId = theaterId;
    }
}

package com.example.movie_reservation.Cinema.model;

import java.util.UUID;

public class Cinema {
    private final UUID cinemaId;
    private final String cinemaName;

    public Cinema(UUID cinemaId, String cinemaName) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
    }
}

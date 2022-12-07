package com.example.movie_reservation.theater.model;

import java.util.UUID;

public class Theater {
    private final UUID theaterId;
    private final String theaterName;

    public Theater(UUID theaterId, String theaterName) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
    }

    public UUID getTheaterId() {
        return theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }
}

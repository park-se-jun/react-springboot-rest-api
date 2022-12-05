package com.example.movie_reservation.cinema.model;

import java.util.UUID;

public class Theater {
    private final UUID theaterId;
    private final int theaterNo;

    public Theater(UUID theaterId, int theaterNo) {
        this.theaterId = theaterId;
        this.theaterNo = theaterNo;
    }
}

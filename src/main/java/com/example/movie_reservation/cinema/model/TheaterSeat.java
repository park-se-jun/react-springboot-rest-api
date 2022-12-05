package com.example.movie_reservation.cinema.model;

import java.util.UUID;

public class TheaterSeat {
    private final UUID theaterId;
    private final UUID seatId;

    public TheaterSeat(UUID theaterId, UUID seatId) {
        this.theaterId = theaterId;
        this.seatId = seatId;
    }
}

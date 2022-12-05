package com.example.movie_reservation.cinema.model;

import java.util.UUID;

public class Seat {
    private final UUID seatId;
    private final String row;
    private final int column;

    public Seat(UUID seatId, String row, int column) {
        this.seatId = seatId;
        this.row = row;
        this.column = column;
    }
}

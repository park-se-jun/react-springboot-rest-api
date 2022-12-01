package com.example.movie_reservation.reservation;

import java.util.UUID;

public class MovieReservation {
    private final UUID movieReservationId;
    private final UUID reservedSeatId;
    private final UUID customerId;
    private final Long price;

    public MovieReservation(UUID movieReservationId, UUID reservedSeatId, UUID customerId, Long price) {
        this.movieReservationId = movieReservationId;
        this.reservedSeatId = reservedSeatId;
        this.customerId = customerId;
        this.price = price;
    }
}

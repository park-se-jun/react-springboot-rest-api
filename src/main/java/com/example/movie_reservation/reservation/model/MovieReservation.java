package com.example.movie_reservation.reservation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class MovieReservation {
    private final UUID movieReservationId;//시리얼 번호 대신에
    private final String phone;
    private final int seatCount;
    private final long price;
    private final LocalDateTime reservationTime;
    public MovieReservation(
            UUID movieReservationId,
            String phone, int seatCount,
            long price,
            LocalDateTime reservationTime) {
        this.movieReservationId = movieReservationId;
        this.phone = phone;
        this.seatCount = seatCount;
        this.price = price;
        this.reservationTime = reservationTime;
    }

    public UUID getMovieReservationId() {
        return movieReservationId;
    }

    public String getPhone() {
        return phone;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public long getPrice() {
        return price;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}

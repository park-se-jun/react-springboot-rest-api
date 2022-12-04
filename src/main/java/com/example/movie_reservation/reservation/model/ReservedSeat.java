package com.example.movie_reservation.reservation.model;

import java.util.UUID;

public class ReservedSeat {
    private final UUID reservedSeatId;
    private final UUID seatId;//스케쥴의 상영관과 연관 있어야 한다.
    private final UUID scheduleId;
    private final String row;
    private final int col;

    public ReservedSeat(UUID reservedSeatId, UUID seatId, UUID scheduleId, String row, int col) {
        this.reservedSeatId = reservedSeatId;
        this.seatId = seatId;
        this.scheduleId = scheduleId;
        this.row = row;
        this.col = col;
    }

    public UUID getReservedSeatId() {
        return reservedSeatId;
    }

    public UUID getSeatId() {
        return seatId;
    }

    public UUID getScheduleId() {
        return scheduleId;
    }

    public String getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

package com.example.movie_reservation.reservation;

import java.util.UUID;

public class ReservedSeat {
    private final UUID reservedSeatId;
    private final UUID seatId;//스케쥴의 상영관과 연관 있어야 한다.
    private final UUID scheduleId;

    public ReservedSeat(UUID reservedSeatId, UUID seatId, UUID scheduleId) {
        this.reservedSeatId = reservedSeatId;
        this.seatId = seatId;
        this.scheduleId = scheduleId;
    }
}

package com.example.movie_reservation.theater.model;

import java.util.UUID;

public class Screen {
    private final UUID screenId;
    private final int screenNo;
    private final UUID theaterId;

    public Screen(UUID screenId, int screenNo, UUID theaterId) {
        this.screenId = screenId;
        this.screenNo = screenNo;
        this.theaterId = theaterId;
    }
}

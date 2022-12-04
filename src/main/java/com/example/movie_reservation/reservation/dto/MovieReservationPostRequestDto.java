package com.example.movie_reservation.reservation.dto;

import java.util.List;
import java.util.UUID;

public record MovieReservationPostRequestDto(
        UUID scheduleId,
        String phoneNumber,
        long price,
        List<String> rowArray,
        List<Integer>colArray
) {
    /**
     * {
     *     scheduleId: 21312312323123,
     *     price : 14000,
     *     rowArray : [],
     *     colArray : [],
     * }
     */
}

package com.example.movie_reservation.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record MovieReservationReadResponseDto(
        UUID movieReservationId,
        String phone, int seatCount,
        long price,
        String movieTitle,
        LocalTime movieStartTime,
        LocalTime movieEndTime,
        LocalDate date,
        List<String> rowArray,
        List<Integer> colArray){
}

package com.example.movie_reservation.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record SchedulesResponseDto(
        UUID scheduleId,
        String title,
        LocalTime startTime,
        LocalTime endTime,
        String theaterName,
        String screenName
) {
    public static SchedulesResponseDto from(Schedule schedule){
        return new SchedulesResponseDto(
                schedule.getScheduleId(),
                schedule.getMovieTitle(),
                schedule.getStartTime().toLocalTime(),
                schedule.getEndTime().toLocalTime(),
                schedule.getTheaterName(),
                schedule.getScreenName());
    }
}

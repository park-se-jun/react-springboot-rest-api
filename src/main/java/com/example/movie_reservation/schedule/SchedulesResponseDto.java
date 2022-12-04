package com.example.movie_reservation.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public record SchedulesResponseDto(
        String title,
        LocalTime startTime,
        LocalTime endTime,
        String theaterName,
        String screenName
) {
    public static SchedulesResponseDto from(Schedule schedule){
        return new SchedulesResponseDto(schedule.getMovieTitle(),
                schedule.getStartTime().toLocalTime(),
                schedule.getEndTime().toLocalTime(),
                schedule.getTheaterName(),
                schedule.getScreenName());
    }
}

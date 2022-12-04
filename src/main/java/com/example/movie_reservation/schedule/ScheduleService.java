package com.example.movie_reservation.schedule;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findSchedules(UUID cinemaId, LocalDate date, UUID movieId) {
        if (Objects.isNull(movieId)) {
            return scheduleRepository.findSchedleByDate(cinemaId, date);
        } else {
            return scheduleRepository.findScheduleByMovieIdAndDate(cinemaId, movieId, date);
        }
    }
    public Optional<Schedule> findScheduleById(UUID scheduleId){
        return scheduleRepository.getScheduleById(scheduleId);
    }

    public Map<String,Boolean[]> getSeatsByScheduleId(UUID scheduleId){
        return scheduleRepository.getSeatsByScheduleId(scheduleId);
    }


}

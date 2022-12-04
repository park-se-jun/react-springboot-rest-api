package com.example.movie_reservation.schedule;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public List<Schedule> findSchedules(UUID cinemaId, LocalDate date, UUID movieId) {
        if (Objects.isNull(movieId)) {
            return scheduleRepository.findSchedleByDate(cinemaId, date);
        } else {
            return scheduleRepository.findScheduleByMovieIdAndDate(cinemaId, movieId, date);
        }
    }
    @Transactional
    public Optional<Schedule> findScheduleById(UUID scheduleId){
        return scheduleRepository.getScheduleById(scheduleId);
    }

    @Transactional
    public Map<String,Boolean[]> getSeatsByScheduleId(UUID scheduleId){
        return scheduleRepository.getSeatsByScheduleId(scheduleId);
    }

    @Transactional
    public void createSchedule(UUID movieId, LocalDateTime startTime, UUID screenId) {
        //나중에 시간이 곂치는지 체크하는 로직 필요함.
        scheduleRepository.createSchedule(UUID.randomUUID(),movieId,startTime,screenId);
    }
}

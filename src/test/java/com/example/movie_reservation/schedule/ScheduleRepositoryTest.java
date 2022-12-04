package com.example.movie_reservation.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class ScheduleRepositoryTest {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Test
    void findScheduleByMovieId() {
        var schedules = scheduleRepository.findScheduleByMovieIdAndDate(
                UUID.fromString("16be9063-735a-11ed-9f9e-0242ac110002"),UUID.fromString("09eff753-7367-11ed-8478-0242ac110002"), LocalDate.now());
        System.out.println("여기에 출력합니다.");
        System.out.println(UUID.fromString("09eff753-7367-11ed-8478-0242ac110002").toString());
        System.out.println(LocalDate.now().toString());
        System.out.println(schedules);
    }

    @Test
    void findSeatsByScheduleId() {
        var seats = scheduleRepository.getSeatsByScheduleId(UUID.fromString("319025e8-738a-11ed-8478-0242ac110002"));
        System.out.println(seats);
    }
}
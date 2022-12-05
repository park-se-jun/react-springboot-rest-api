package com.example.movie_reservation.cinema;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "${apiPrefix}")
public class CinemaController {
    //스케쥴에 따른 좌석 현황을 가져오기
    @GetMapping(value = "/seats/{scheduleId}")
    public SeatsResponseDto getSeatListBySchedule(@PathVariable("scheduleId") UUID scheduleId){
        return null;
    }

}

package com.example.movie_reservation.schedule;

import com.example.movie_reservation.Cinema.SeatsResponseDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "${apiPrefix}")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //스케쥴 리스트 조회 read
    @GetMapping("schedules")
    public List<SchedulesResponseDto> getSchedules(
            @RequestParam("cinemaId") UUID cinemaId,
            @RequestParam(value = "date",defaultValue = "#{T(java.time.LocalDate).now()}")LocalDate date,
            @RequestParam(value = "movieId",required = false) UUID movieId
    ){
        var schedules = scheduleService.findSchedules(cinemaId, date, movieId);
        return schedules.stream().map(SchedulesResponseDto::from).toList();
    }

    @GetMapping("schedules/{scheduleId}")
    public SchedulesResponseDto findScheduleById(@PathVariable("scheduleId")UUID scheduleId){
        var schedule = scheduleService.findScheduleById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스케쥴을 찾을 수 없습니다."));
        return SchedulesResponseDto.from(schedule);
    }

    @GetMapping("schedules/{scheduleId}/seats")
    public SeatsResponseDto getSeatsByScheduleId(@PathVariable("scheduleId")UUID scheduleId){
        var seats = scheduleService.getSeatsByScheduleId(scheduleId);
        return SeatsResponseDto.from(seats);
    }
    //스케쥴 생성(관리자) create

    //스케쥴 삭제(관리자) delete

    //스케쥴 업데이트(관리자)

}

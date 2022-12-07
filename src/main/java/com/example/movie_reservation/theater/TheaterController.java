package com.example.movie_reservation.theater;

import com.example.movie_reservation.theater.dto.TheaterGetResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${apiPrefix}")
public class TheaterController {
    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    //스케쥴에 따른 좌석 현황을 가져오기
    @GetMapping("/theaters")
    public List<TheaterGetResponseDto> getAllTheaterList(){
        return theaterService.getAllTheaterList();
    }

}

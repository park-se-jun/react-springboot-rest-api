package com.example.movie_reservation.reservation;

import com.example.movie_reservation.reservation.dto.MovieReservationCreateResponseDto;
import com.example.movie_reservation.reservation.dto.MovieReservationPostRequestDto;
import com.example.movie_reservation.reservation.dto.MovieReservationReadResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //영화 티켓 예매하기
    //필요한 데이터
    //필요 정보 : 스케쥴 정보, 전화번호, 좌석정보
    //반환 정보 : 생성된 티켓 1개의 정보
    @PostMapping("/reservations")
    public MovieReservationCreateResponseDto makeMovieReservation(@RequestBody MovieReservationPostRequestDto requestDto){
        var scheduleId = requestDto.scheduleId();

        reservationService.createMovieReservation(requestDto);
        return null;
    }
    @GetMapping("/reservations/lookup/{userPhone}")
    public List<MovieReservationReadResponseDto> getReservationByUserPhoneNumber(@RequestParam("userPhone")String phoneNumber){
        return reservationService.findMovieReservationByPhoneNumber(phoneNumber);
    }
    //전화번호로 티켓 조회
    //필요 정보 : 전화번호
    //반환 정보 : 해당하는 List 값,(영화 표 관련 정보 전부)


}

package com.example.movie_reservation.reservation;

import com.example.movie_reservation.reservation.dto.MovieReservationPostRequestDto;
import com.example.movie_reservation.reservation.dto.MovieReservationReadResponseDto;
import com.example.movie_reservation.reservation.model.MovieReservation;
import com.example.movie_reservation.reservation.model.ReservedSeat;
import com.example.movie_reservation.schedule.Schedule;
import com.example.movie_reservation.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;

    public ReservationService(ReservationRepository reservationRepository, ScheduleRepository scheduleRepository) {
        this.reservationRepository = reservationRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public UUID createMovieReservation(MovieReservationPostRequestDto requestDto) {

        int seatCount = requestDto.colArray().size();
        return reservationRepository.createMovieReservation(
                UUID.randomUUID(),
                requestDto.price(),
                LocalDateTime.now(),
                seatCount,
                requestDto.phoneNumber(),
                requestDto.scheduleId(),
                requestDto.rowArray(),
                requestDto.colArray());
    }

    public List<MovieReservationReadResponseDto> findMovieReservationByPhoneNumber(String phoneNumber) {
        List<MovieReservationReadResponseDto> returnValue = new ArrayList<>();

        var movieReservations = reservationRepository.findMovieReservationByPhoneNumber(phoneNumber);
        for (MovieReservation movieReservation : movieReservations) {
            var movieReservationId = movieReservation.getMovieReservationId();
            var reservedSeats = reservationRepository.getReservedSeatByMovieReservationId(movieReservationId);
            List<String> rowArray = new ArrayList<>();
            List<Integer> colArray = new ArrayList<>();
            Schedule schedule = null;
            for (ReservedSeat reservedSeat : reservedSeats) {
                rowArray.add(reservedSeat.getRow());
                colArray.add(reservedSeat.getCol());
                if (schedule == null) {
                    schedule = scheduleRepository.getScheduleById(reservedSeat.getScheduleId()).
                            orElseThrow(() -> new RuntimeException("scheduleId로 schedule을 찾을 수 없습니다."));
                }
            }
            assert schedule != null;
            var readResponseDto = new MovieReservationReadResponseDto(movieReservationId,
                    phoneNumber,
                    movieReservation.getSeatCount(),
                    movieReservation.getPrice(),
                    schedule.getScreenName(),
                    schedule.getMovieTitle(),
                    schedule.getStartTime().toLocalTime(),
                    schedule.getEndTime().toLocalTime(),
                    schedule.getStartTime().toLocalDate(),
                    rowArray,
                    colArray);
            returnValue.add(readResponseDto);
        }
        return returnValue;

    }
}



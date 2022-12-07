package com.example.movie_reservation.theater;

import com.example.movie_reservation.theater.dto.TheaterGetResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public List<TheaterGetResponseDto> getAllTheaterList(){
        return theaterRepository
                .getAllTheaterList()
                .stream().map(TheaterGetResponseDto::from)
                .toList();
    }
}

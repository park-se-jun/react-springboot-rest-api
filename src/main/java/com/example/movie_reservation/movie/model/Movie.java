package com.example.movie_reservation.movie.model;

import java.time.LocalDate;
import java.util.UUID;

public class Movie {
    private final UUID id;
    private final String title;
    private final LocalDate releaseDate;
    private final String posterUrl;
    private String desc;

    public Movie(UUID id, String title, LocalDate releaseDate, String posterUrl) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.desc = "";
    }

    public Movie(UUID id, String title, LocalDate releaseDate, String posterUrl, String desc) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.desc = desc;
    }
}

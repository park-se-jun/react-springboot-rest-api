package com.example.movie_reservation.movie.model;

import java.time.LocalDate;
import java.util.UUID;

public class Movie {
    private final UUID id;
    private final String title;
    private final LocalDate releaseDate;
    private final String posterUrl;



    private String desc;
    private boolean showing = false;

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

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isShowing() {
        return showing;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }
}

package com.example.movie_reservation.movie.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Movie {
    private final UUID id;
    private final String title;
    private final LocalDate releaseDate;
    private final String posterUrl;
    private String desc;
    private final Time runningTime;
    private boolean showingStatus = false;

//    public Movie(UUID id, String title, LocalDate releaseDate, String posterUrl, LocalDateTime runningTime) {
//        this.id = id;
//        this.title = title;
//        this.releaseDate = releaseDate;
//        this.posterUrl = posterUrl;
//        this.desc = "";
//        this.runningTime = runningTime;
//    }

    public Movie(UUID id, String title, LocalDate releaseDate, String posterUrl, String desc, Time runningTime,boolean showingStatus) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.desc = desc;
        this.runningTime = runningTime;
        this.showingStatus = showingStatus;
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
        return showingStatus;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setShowingStatus(boolean showing) {
        this.showingStatus = showing;
    }
}

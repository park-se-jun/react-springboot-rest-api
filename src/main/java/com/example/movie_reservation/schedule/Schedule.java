package com.example.movie_reservation.schedule;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Schedule {
    private final UUID scheduleId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String movieTitle;
    private final UUID screenId;
    private final String theaterName;
    private final String screenName;
    public Schedule(UUID scheduleId, LocalDateTime startTime, LocalDateTime endTime, String movieTitle, UUID screenId, String theaterName, String screenName) {
        this.scheduleId = scheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieTitle = movieTitle;
        this.theaterName = theaterName;
        this.screenName = screenName;
        this.screenId = screenId;
    }

    public UUID getScheduleId() {
        return scheduleId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public UUID getScreenId() {
        return screenId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public String getScreenName() {
        return screenName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schedule{");
        sb.append("scheduleId=").append(scheduleId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", movieTitle='").append(movieTitle).append('\'');
        sb.append(", screenId=").append(screenId);
        sb.append(", theaterName='").append(theaterName).append('\'');
        sb.append(", screenName='").append(screenName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(scheduleId, schedule.scheduleId) && Objects.equals(startTime, schedule.startTime) && Objects.equals(endTime, schedule.endTime) && Objects.equals(movieTitle, schedule.movieTitle) && Objects.equals(screenId, schedule.screenId) && Objects.equals(theaterName, schedule.theaterName) && Objects.equals(screenName, schedule.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, startTime, endTime, movieTitle, screenId, theaterName, screenName);
    }
}

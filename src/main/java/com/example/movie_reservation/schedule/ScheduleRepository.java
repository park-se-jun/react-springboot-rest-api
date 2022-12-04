package com.example.movie_reservation.schedule;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static com.example.gccoffee.repository.JdbcUtils.toUUID;

@Repository
public class ScheduleRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Schedule> scheduleRowMapper = (rs, rowNum) -> {
        var scheduleId = toUUID(rs.getBytes("movie_schedule_id"));
        var startTime = rs.getTimestamp("start_time").toLocalDateTime();
        var runningTime = rs.getTime("running_time").toLocalTime();
        var endTime = startTime.plusSeconds(runningTime.toSecondOfDay());
        var title = rs.getString("title");
        var screenId = toUUID(rs.getBytes("screen_id"));
        var theaterName = rs.getString("theater_name");
        var screenName = rs.getString("screen_name");
        return new Schedule(
                scheduleId,
                startTime,
                endTime,
                title,
                screenId,
                theaterName,
                screenName
        );

    };

    public ScheduleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //    public Schedule insert(Schedule schedule){
//        jdbcTemplate.update()
//    }
    public Optional<Schedule> getScheduleById(UUID scheduleId) {
        try {
            var schedule = jdbcTemplate.queryForObject(
                    "select ms.movie_schedule_id, ms.start_time, m.running_time," +
                            "m.title,s.screen_id,t.theater_name,s.screen_name from movie_schedule as ms " +
                            "join movie m on ms.movie_movie_id = m.movie_id " +
                            "join screen s on s.screen_id = ms.screen_screen_id " +
                            "join theater t on s.theater_theater_id = t.theater_id " +
                            "where ms.movie_schedule_id=UUID_TO_BIN(:scheduleId);",
                    Collections.singletonMap("scheduleId", scheduleId),
                    scheduleRowMapper);
            return Optional.of(schedule);
        } catch (DataAccessException e) {
            return Optional.empty();
        }

    }

    @Transactional
    public Map<String, Boolean[]> getSeatsByScheduleId(UUID scheduleId) {
        Map<String, Boolean[]> resultMap = new HashMap<>();
        jdbcTemplate.query(
                "select ms.movie_schedule_id, seat.seat_row,seat.seat_col from movie_schedule ms" +
                        "    join screen s on s.screen_id = ms.screen_screen_id" +
                        "    join seat on seat.screen_screen_id = s.screen_id " +
                        "where movie_schedule_id = UUID_TO_BIN(:scheduleId) order by  seat_row ,seat_col desc;",
                Collections.singletonMap("scheduleId", scheduleId.toString()),
                (rs, rowNum) -> {
                    var seatRow = rs.getString("seat_row");
                    var seatCol = rs.getInt("seat_col");
                    if (resultMap.containsKey(seatRow)) {
                        return null;
                    } else {
                        var booleans = new Boolean[seatCol];
                        Arrays.fill(booleans, true);
                        resultMap.put(seatRow, booleans);
                    }
                    return null;
                }
        );
        jdbcTemplate.query(
                "select s.seat_row,s.seat_col from reserved_seat rs " +
                        "join seat s on rs.seat_seat_id = s.seat_id " +
                        "where movie_schedule_movie_schedule_id = UUID_TO_BIN(:scheduleId)",
                Collections.singletonMap("scheduleId", scheduleId.toString()),
                (rs, rowNum) -> {
                    var seatRow = rs.getString("seat_row");
                    var seatCol = rs.getInt("seat_col");
                    resultMap.get(seatRow)[seatCol] = false;
                    return null;
                }
        );
        return resultMap;
    }

    @Transactional
    public List<Schedule> findSchedleByDate(UUID cinemaId, LocalDate date) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("date", date.toString());
        paramMap.put("cinemaId", cinemaId.toString());

        return jdbcTemplate.query(
                "select ms.movie_schedule_id, ms.start_time, m.running_time," +
                        "m.title,s.screen_id,t.theater_name,s.screen_name from movie_schedule as ms " +
                        "join movie m on ms.movie_movie_id = m.movie_id " +
                        "join screen s on s.screen_id = ms.screen_screen_id " +
                        "join theater t on s.theater_theater_id = t.theater_id " +
                        "where (DATE(start_time) =:date) and s.theater_theater_id = UUID_TO_BIN(:cinemaId)",
                paramMap,
                scheduleRowMapper);
    }

    @Transactional
    public List<Schedule> findScheduleByMovieIdAndDate(UUID cinemaId,UUID movieId, LocalDate date) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("movieId", movieId.toString());
        paramMap.put("date", date.toString());
        paramMap.put("cinemaId", cinemaId.toString());

        return jdbcTemplate.query(
                "select ms.movie_schedule_id, ms.start_time, m.running_time," +
                        "m.title,s.screen_id,t.theater_name,s.screen_name from movie_schedule as ms " +
                        "join movie m on ms.movie_movie_id = m.movie_id " +
                        "join screen s on s.screen_id = ms.screen_screen_id " +
                        "join theater t on s.theater_theater_id = t.theater_id " +
                        "where (ms.movie_movie_id=UUID_TO_BIN(:movieId)) " +
                        "AND (DATE(start_time) =:date)" +
                        "AND (s.theater_theater_id = UUID_TO_BIN(:cinemaId))",
                paramMap,
                scheduleRowMapper);
    }
}

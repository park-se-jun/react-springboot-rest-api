package com.example.movie_reservation.theater;

import com.example.gccoffee.repository.JdbcUtils;
import com.example.movie_reservation.theater.model.Theater;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TheaterRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TheaterRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Theater> getAllTheaterList() {
        return jdbcTemplate.getJdbcTemplate().query("select * from theater",(rs, rowNum) -> {
            var theaterId = JdbcUtils.toUUID(rs.getBytes("theater_id"));
            var theaterName = rs.getString("theater_name");
            return new Theater(theaterId,theaterName);
        });
    }
}

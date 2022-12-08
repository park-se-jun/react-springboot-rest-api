package com.example.movie_reservation.movie;

import com.example.movie_reservation.movie.model.Movie;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.movie_reservation.utils.JdbcUtils.*;
@Repository
public class MovieRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MovieRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getActiveMovieList() {
        return jdbcTemplate.
                getJdbcTemplate().
                query("select * from movie where status=true",
                        (rs, rowNum) -> {
                            var movieId = toUUID(rs.getBytes("movie_id"));
                            var title = rs.getString("title");
                            var desc = rs.getString("desc");
                            var runningTime = rs.getTime("running_time");
                            var posterImageUrl = rs.getString("poster_image_url");
                            var releaseDate = rs.getTimestamp("release_date").toLocalDateTime().toLocalDate();
                            var status = rs.getBoolean("status");
                            return new Movie(
                                    movieId,
                                    title,
                                    releaseDate,
                                    posterImageUrl,
                                    desc,
                                    runningTime,
                                    status);
                        });
    }


}

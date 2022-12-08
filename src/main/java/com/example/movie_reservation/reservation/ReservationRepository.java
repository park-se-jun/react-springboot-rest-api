package com.example.movie_reservation.reservation;

import com.example.movie_reservation.reservation.model.MovieReservation;
import com.example.movie_reservation.reservation.model.ReservedSeat;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.movie_reservation.utils.JdbcUtils.toUUID;

@Repository
public class ReservationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ReservationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public UUID createMovieReservation(
            UUID movieTicketId,
            long price,
            LocalDateTime reservationTime,
            int seatCount,
            String phoneNumber,
            UUID scheduleId,
            List<String> rowArray,
            List<Integer> colArray
    ) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("movieTicketId", movieTicketId.toString());
        paramMap.put("price", price);
        paramMap.put("reservationTime", reservationTime);
        paramMap.put("seatCount", seatCount);
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("scheduleId", scheduleId.toString());
        var updatedFirst = jdbcTemplate.update(
                "insert into movie_ticket(movie_ticket_id,price,user_phone_number,seat_count,reservation_time) " +
                        "values(UUID_TO_BIN(:movieTicketId),:price,:phoneNumber,:seatCount,:reservationTime)",
                paramMap);
        // 제대로 넣어졌다면? -> 예약석도 넣어야함
        if (updatedFirst == 1) {

            for (int i = 0; i < rowArray.size(); i++) {
                paramMap.put("row", rowArray.get(i));
                paramMap.put("col", colArray.get(i));
                var update = jdbcTemplate.update(
                        """
                                insert into reserved_seat(movie_ticket_movie_ticket_id,movie_schedule_movie_schedule_id,seat_seat_id)
                                select UUID_TO_BIN(:movieTicketId),
                                       UUID_TO_BIN(:scheduleId),
                                       s.seat_id
                                from seat s join movie_schedule ms on
                                            ms.movie_schedule_id=UUID_TO_BIN(:scheduleId) and
                                            s.screen_screen_id = ms.screen_screen_id
                                where s.seat_row = :row
                                  and s.seat_col = :col;""",
                        paramMap
                );
                System.out.println(update);
                if (update != 1) {
                    throw new RuntimeException("삽입 실패");
                }
            }
        }

        return movieTicketId;
    }

    public List<MovieReservation> findMovieReservationByPhoneNumber(String phoneNumber) {

        return jdbcTemplate.query(
                "select  * from movie_ticket where user_phone_number = :phoneNumber",
                Collections.singletonMap("phoneNumber", phoneNumber),
                (rs, rowNum) -> {
                    var ticketId = toUUID(rs.getBytes("movie_ticket_id"));
                    var userPhoneNumber = rs.getString("user_phone_number");
                    var seatCount = rs.getInt("seat_count");
                    var price = rs.getLong("price");
                    var reservationTime = rs.getTimestamp("reservation_time").toLocalDateTime();

                    return new MovieReservation(ticketId, userPhoneNumber, seatCount, price, reservationTime);
                }
        );
    }

    public List<ReservedSeat> getReservedSeatByMovieReservationId(UUID reservationId) {
        return jdbcTemplate.query(
                "select s.seat_col,s.seat_row, reserved_seat_id,s.seat_id,movie_schedule_movie_schedule_id from reserved_seat join seat s on s.seat_id = seat_seat_id where movie_ticket_movie_ticket_id=UUID_TO_BIN(:reservationId)",
                Collections.singletonMap("reservationId", reservationId.toString()),
                (rs, rowNum) -> {
                    var reservedSeatId = toUUID(rs.getBytes("reserved_seat_id"));
                    var seatRow = rs.getString("seat_row");
                    var seatCol = rs.getInt("seat_col");
                    var scheduleId = toUUID(rs.getBytes("movie_schedule_movie_schedule_id"));
                    var seatId = toUUID(rs.getBytes("seat_id"));
                    return new ReservedSeat(reservedSeatId, seatId, scheduleId, seatRow, seatCol);
                });
    }
}

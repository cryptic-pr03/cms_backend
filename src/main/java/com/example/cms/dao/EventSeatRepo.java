package com.example.cms.dao;

import com.example.cms.Models.EventSeat;
import com.example.cms.Models.Seat;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EventSeatRepo implements EventSeatDAO{

    private final JdbcTemplate jdbcTemplate;
    private final TakesPlaceDAO takesPlaceRepo;
    private final SeatDAO seatRepo;

    public EventSeatRepo(JdbcTemplate jdbcTemplate, TakesPlaceDAO takesPlaceRepo, SeatDAO seatRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.takesPlaceRepo = takesPlaceRepo;
        this.seatRepo = seatRepo;
    }

    private static class EventSeatMapper implements RowMapper<EventSeat>{
        @Override
        public EventSeat mapRow(ResultSet rs, int rowNum) throws SQLException {
            EventSeat eventSeat = new EventSeat();
            eventSeat.setSeatId(rs.getInt("seatId"));
            eventSeat.setEventId(rs.getInt("eventId"));
            eventSeat.setBooked(rs.getBoolean("isBooked"));
            eventSeat.setPrice(rs.getInt("price"));
            return eventSeat;
        }
    }

    @Override
    public List<Map<String, Object>> getEventSeatDetails(int eventId) throws CustomException {
        String sql =
                "SELECT es.seatId, es.eventId, es.isBooked, es.price, s.seatType " +
                "FROM EventSeat es, Seat s WHERE es.eventId = ? " +
                "AND es.seatId = s.seatId " +
                "AND s.venueId = (SELECT t.venueId FROM TakesPlace t WHERE t.eventId = es.eventId) ";

        try{
            return jdbcTemplate.queryForList(sql, eventId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public EventSeat getSeat(int seatId, int eventId) throws CustomException {
        String sql = "SELECT * FROM EventSeat WHERE seatId = ? AND eventId = ?";

        try{
            List<EventSeat> ls = jdbcTemplate.query(sql, new EventSeatMapper(), seatId, eventId);
            if(ls.size() == 0){
                return null;
            }
            else{
                return ls.get(0);
            }
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public EventSeat updateSeat(int seatId, int eventId, EventSeat updatedEventSeat) throws CustomException {
        String sql = "UPDATE EventSeat SET seatId = ?, eventId = ?, isBooked = ?, price = ? " +
                "WHERE seatId = ? AND eventId = ?";
        int isUpdated;
        try {
            isUpdated = jdbcTemplate.update(sql, updatedEventSeat.getSeatId(), updatedEventSeat.getEventId(),
                    updatedEventSeat.isBooked(), updatedEventSeat.getPrice(), seatId, eventId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isUpdated == 0){
            throw new CustomException("Could not update Event-Seat value");
        }
        return updatedEventSeat;
    }

    @Override
    @Transactional
    public List<EventSeat> bookSeats(List<Integer> seatIds, int eventId) throws CustomException {
        String sql = "UPDATE EventSeat SET isBooked = TRUE" +
                "WHERE seatId = ? AND eventId = ?";
        try {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, seatIds.get(i));
                    ps.setInt(2, eventId);
                }

                public int getBatchSize() {
                    return seatIds.size();
                }
            });
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        List<EventSeat> ls = new ArrayList<>();
        for(int i : seatIds) ls.add(getSeat(i, eventId));
        return ls;
    }

    @Override
    @Transactional
    public void initEventSeats(int eventId) throws CustomException {
        int venueId = takesPlaceRepo.getVenueIdOfEvent(eventId);

        List<Seat> ls = seatRepo.getSeatByVenueId(venueId);

        String sql = "INSERT INTO EventSeat(seatId, eventId) VALUES(?, ?)";

        try {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Seat s = ls.get(i);
                    ps.setInt(1, s.getSeatId());
                    ps.setInt(2, eventId);
                }

                public int getBatchSize() {
                    return ls.size();
                }
            });
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void deleteEventSeats(int eventId) throws CustomException {
        String sql = "DELETE FROM EventSeat WHERE eventId = ?";

        try {
            jdbcTemplate.update(sql, eventId);
        } catch (DataAccessException e) {
            throw new CustomException(e.getMessage());
        }
    }
}

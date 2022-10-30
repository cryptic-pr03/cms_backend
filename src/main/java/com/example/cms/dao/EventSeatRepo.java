package com.example.cms.dao;

import com.example.cms.Models.Event;
import com.example.cms.Models.EventSeat;
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

@Repository
public class EventSeatRepo implements EventSeatDAO{

    private final JdbcTemplate jdbcTemplate;
    private final TakesPlaceDAO takesPlaceRepo;

    public EventSeatRepo(JdbcTemplate jdbcTemplate, TakesPlaceDAO takesPlaceRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.takesPlaceRepo = takesPlaceRepo;
    }

    private static class EventSeatMapper implements RowMapper<EventSeat>{
        @Override
        public EventSeat mapRow(ResultSet rs, int rowNum) throws SQLException {
            EventSeat eventSeat = new EventSeat();
            eventSeat.setSeatId(rs.getInt("seatId"));
            eventSeat.setEventId(rs.getInt("eventId"));
            eventSeat.setBooked(rs.getBoolean("isBooked"));
            eventSeat.setEventId(rs.getInt("price"));
            return eventSeat;
        }
    }

    @Override
    public List<EventSeat> getEventSeatDetails(int eventId) throws CustomException {
        String sql = "SELECT * FROM EventSeat WHERE eventId = ?";

        try{
            return jdbcTemplate.query(sql, new EventSeatMapper(), eventId);
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
    public void initEventSeats(int eventId) throws CustomException {

    }

    @Override
    public void deleteEventSeats(int eventId) throws CustomException {

    }
}

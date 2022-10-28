package com.example.cms.dao;

import com.example.cms.Models.EventSeat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventSeatRepo implements EventSeatDAO{

    private final JdbcTemplate jdbcTemplate;

    public EventSeatRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EventSeat> getEventSeatDetails(int eventId) throws CustomException {
        return null;
    }

    @Override
    public EventSeat getSeat(int seatId, int eventId) throws CustomException {
        return null;
    }

    @Override
    public EventSeat updateSeat(int seatId, int eventId, EventSeat newEventSeat) throws CustomException {
        return null;
    }

    @Override
    public List<EventSeat> bookSeats(List<Integer> seatIds, int eventId) throws CustomException {
        return null;
    }

    @Override
    public void initEventSeats(int eventId) throws CustomException {

    }

    @Override
    public void deleteEventSeats(int eventId) throws CustomException {

    }
}

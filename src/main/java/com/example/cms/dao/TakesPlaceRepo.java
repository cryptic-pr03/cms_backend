package com.example.cms.dao;


import com.example.cms.Models.Event;
import com.example.cms.Models.TakesPlace;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TakesPlaceRepo implements TakesPlaceDAO{
    private final JdbcTemplate jdbcTemplate;

    public TakesPlaceRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class TakesPlaceMapper implements RowMapper<TakesPlace> {
        @Override
        public TakesPlace mapRow(ResultSet rs, int rowNum) throws SQLException {
            TakesPlace takesPlace = new TakesPlace();
            takesPlace.setVenueId(rs.getInt("venueId"));
            takesPlace.setSlotId(rs.getInt("slotId"));
            takesPlace.setEventId(rs.getInt("eventId"));
            return takesPlace;
        }
    }

    static class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            return getEvent(rs);
        }

        static Event getEvent(ResultSet rs) throws SQLException {
            Event event = new Event();
            event.setEventId(rs.getInt("eventId"));
            event.setEventName(rs.getString("name"));
            event.setEventStartTime(rs.getTime("startTime"));
            event.setEventEndTime(rs.getTime("endTime"));
            event.setEventDate(rs.getDate("eventDate"));
            event.setEventAge(rs.getInt("ageLimit"));
            event.setEventLogoUrl(rs.getString("logoUrl"));
            return event;
        }
    }

    @Override
    public TakesPlace addTakesPlace(TakesPlace newTakesPlace) throws CustomException {
        String sql = "INSERT INTO TakesPlace(venueId, slotId, eventId) VALUES(?, ?, ?)";
        int isAdded;
        try {
            isAdded = jdbcTemplate.update(sql, newTakesPlace.getVenueId(), newTakesPlace.getSlotId(), newTakesPlace.getEventId());
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        if(isAdded==0){
            throw new CustomException("Could not add event's occurrence place");
        }
        return newTakesPlace;
    }

    @Override
    public List<Event> getEventsHeldInVenue(int venueId) throws CustomException{
        String sql = "SELECT eventId FROM TakesPlace WHERE venueId = ?";
        List<Integer> eventIds = jdbcTemplate.queryForList(sql, Integer.class, venueId);
//        System.out.println(eventIds);
        String sql1 = "SELECT * FROM Event WHERE eventId = ?";
        try {
            List<Event> eventList = new ArrayList<Event>();
            List<Event> event;
            for (Integer eventId : eventIds) {
                event = jdbcTemplate.query(sql1, new EventMapper(), eventId);
                eventList.add(event.get(0));
            }
            return eventList;
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public <T> List<TakesPlace> getTakesPlaceByAttribute(String attributeName, T attributeValue) {
        String sql = "SELECT * FROM TakesPlace WHERE " + attributeName + " = ?";
        return jdbcTemplate.query(sql, new TakesPlaceMapper(), attributeValue);
    }
}

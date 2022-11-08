package com.example.cms.dao;

import com.example.cms.Models.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class EventRepo implements EventDAO {

    private final JdbcTemplate jdbcTemplate;

    public EventRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setEventId(rs.getInt("eventId"));
            event.setEventName(rs.getString("name"));
            event.setEventStartTime(rs.getTime("startTime"));
            event.setEventEndTime(rs.getTime("endTime"));
            event.setEventDate(rs.getDate("eventDate"));
            event.setEventAge(rs.getInt("ageLimit"));
            event.setDescription(rs.getString("description"));
            event.setEventLogoUrl(rs.getString("logoUrl"));
            return event;
        }
    }

    @Override
    public Event addEvent(Event newEvent) throws CustomException {
        String sql = "INSERT INTO Event(name, startTime, endTime, eventDate, ageLimit, description, logoUrl) VALUES(?,?,?,?,?,?,?)";

        int isCreated;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {

            isCreated = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, newEvent.getEventName());
                ps.setTime(2, newEvent.getEventStartTime());
                ps.setTime(3, newEvent.getEventEndTime());
                ps.setDate(4, newEvent.getEventDate());
                ps.setInt(5, newEvent.getEventAge());
                ps.setString(6, newEvent.getDescription());
                ps.setString(7, newEvent.getEventLogoUrl());

                return ps;
            }, keyHolder);

        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isCreated == 0 || keyHolder.getKey() == null){
            throw new CustomException("Event not inserted");
        }

        newEvent.setEventId(keyHolder.getKey().intValue());
//        System.out.println(newEvent);
        return newEvent;
    }

    @Override
    public Event updateEvent(int eventId, Event updatedEvent) throws CustomException {
        String sql = "UPDATE Event " +
                "SET name = ?, startTime = ?, endTime = ?, eventDate = ?, ageLimit = ?, description = ?, logoUrl = ?" +
                "WHERE eventId = ?";

        int isUpdated;
        try {
            isUpdated = jdbcTemplate.update(sql, updatedEvent.getEventName(), updatedEvent.getEventStartTime(), updatedEvent.getEventEndTime(),
                    updatedEvent.getEventDate(), updatedEvent.getEventAge(), updatedEvent.getDescription(), updatedEvent.getEventLogoUrl(), eventId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isUpdated == 0){
            throw new CustomException("Could not update event");
        }
        return updatedEvent;
    }

    @Override
    public Boolean deleteEvent(int eventId) throws CustomException {
        String sql = "DELETE FROM Event WHERE eventId = ?";

        int isDeleted;
        try{
            isDeleted = jdbcTemplate.update(sql, eventId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        return isDeleted != 0;
    }

    @Override
    public List<Map<String, Object>> getAllEvents() {
        List<Map<String, Object>> res;

        String sql =
                "SELECT eventId, e.name AS eventName, startTime, endTime, ageLimit, eventDate, description, " +
                "logoUrl, venueId, v.name AS venueName, (silverSeats + goldSeats + platinumSeats) AS capacity, city, " +
                "landmark, state, isFunctional, picSeatMatrixUrl, userId, firstName, lastName, email, " +
                "contactNo FROM Event e, Venue v, User u WHERE u.userId = " +
                "(SELECT userId FROM Transaction tr WHERE tr.eventId = e.eventId AND type LIKE 'ARTIST_MANAGER') " +
                "AND v.venueId = (SELECT DISTINCT tp.venueId FROM TakesPlace tp WHERE tp.eventId = e.eventId) " +
                "ORDER BY eventDate DESC";

        res = jdbcTemplate.queryForList(sql);

        for(Map<String, Object> obj : res) {
            int eventId = (int) obj.get("eventId");
            List<String> sp = jdbcTemplate.queryForList("SELECT sponsorName FROM Sponsor WHERE eventId = ?", String.class, eventId);
            obj.put("sponsors", sp);
        }

        return res;
    }

    @Override
    public Event getEventById(int eventId) throws CustomException {
        String sql = "SELECT * FROM Event WHERE eventId = ?";

        try{
            List<Event> ls = jdbcTemplate.query(sql, new EventMapper(), eventId);
            if (ls.size() == 0) {
                return null;
            } else {
                return ls.get(0);
            }
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public <T> List<Event> getEventByAttribute(String attributeName, T attributeValue) throws CustomException
    {
        String sql = "SELECT * FROM Event WHERE " + attributeName + " = ?";
        try{
            return jdbcTemplate.query(sql, new EventMapper(), attributeValue);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}

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
      event.setName(rs.getString("name"));
      event.setStartTime(rs.getTime("startTime"));
      event.setEndTime(rs.getTime("endTime"));
      event.setEventDate(rs.getDate("eventDate"));
      event.setAgeLimit(rs.getInt("ageLimit"));
      event.setDescription(rs.getString("description"));
      event.setLogoUrl(rs.getString("logoUrl"));
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
        ps.setString(1, newEvent.getName());
        ps.setTime(2, newEvent.getStartTime());
        ps.setTime(3, newEvent.getEndTime());
        ps.setDate(4, newEvent.getEventDate());
        ps.setInt(5, newEvent.getAgeLimit());
        ps.setString(6, newEvent.getDescription());
        ps.setString(7, newEvent.getLogoUrl());

        return ps;
      }, keyHolder);

    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }

    if (isCreated == 0 || keyHolder.getKey() == null) {
      throw new CustomException("Event not inserted");
    }

    newEvent.setEventId(keyHolder.getKey().intValue());
    return newEvent;
  }

  @Override
  public Event updateEvent(int eventId, Event updatedEvent) throws CustomException {
    String sql = "UPDATE Event " +
        "SET name = ?, startTime = ?, endTime = ?, eventDate = ?, ageLimit = ?, description = ?, logoUrl = ?" +
        "WHERE eventId = ?";

    int isUpdated;
    try {
      isUpdated = jdbcTemplate.update(sql, updatedEvent.getName(), updatedEvent.getStartTime(),
          updatedEvent.getEndTime(),
          updatedEvent.getEventDate(), updatedEvent.getAgeLimit(), updatedEvent.getDescription(),
          updatedEvent.getLogoUrl(), eventId);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }

    if (isUpdated == 0) {
      throw new CustomException("Could not update event");
    }
    return updatedEvent;
  }

  @Override
  public Boolean deleteEvent(int eventId) throws CustomException {
    String sql = "DELETE FROM Event WHERE eventId = ?";

    int isDeleted;
    try {
      isDeleted = jdbcTemplate.update(sql, eventId);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }

    return isDeleted != 0;
  }

  @Override
  public List<Map<String, Object>> getAllEvents() {
    List<Map<String, Object>> res;
    System.out.println("ok1");

    // add v.seatMatrixDescription e.description
    String sql = "SELECT eventId, e.name AS eventName, startTime, endTime, ageLimit, description, " +
        "logoUrl, venueId, v.name AS venueName, (silverSeats + goldSeats + platinumSeats) as capacity, city, landmark, "
        +
        "state, isFunctional, picSeatMatrixUrl, userId, firstName, lastName, email, " +
        "contactNo FROM Event e, Venue v, User u WHERE u.userId in " +
        "(SELECT userId FROM Transaction tr WHERE tr.eventId = e.eventId AND type LIKE 'ARTIST_MANAGER') " +
        "AND v.venueId in (SELECT tp.venueId FROM TakesPlace tp WHERE tp.eventId = e.eventId)";

    // System.out.println(jdbcTemplate.queryForList(sql));
    res = jdbcTemplate.queryForList(sql);

    for (Map<String, Object> obj : res) {
      int eventId = (int) obj.get("eventId");
      List<String> sp = jdbcTemplate.queryForList("SELECT sponsorName FROM Sponsor WHERE eventId = ?", String.class,
          eventId);
      obj.put("sponsors", sp);
    }
    System.out.println(res);
    return res;
  }

  @Override
  public Map<String, Object> getEventDetails(int eventId) {

    System.out.println(eventId);
    // add v.seatMatrixDescription e.description
    String sql = "SELECT eventId, e.name AS eventName, startTime, endTime, ageLimit, description,eventDate, " +
        "logoUrl, venueId, v.name AS venueName, (silverSeats + goldSeats + platinumSeats) as capacity, city, landmark, "
        +
        "state, isFunctional, picSeatMatrixUrl, userId, firstName, lastName, email, " +
        "contactNo FROM Event e, Venue v, User u WHERE e.eventId = ? AND u.userId in" +
        "(SELECT userId FROM Transaction tr WHERE tr.eventId = e.eventId AND type LIKE 'ARTIST_MANAGER') " +
        "AND v.venueId in (SELECT tp.venueId FROM TakesPlace tp WHERE tp.eventId = e.eventId)";
    Map<String, Object> res = jdbcTemplate.queryForMap(sql, eventId);

    List<String> sp = jdbcTemplate.queryForList("SELECT sponsorName FROM Sponsor WHERE eventId = ?", String.class,
        eventId);
    res.put("sponsors", sp);

    return res;
  }

  @Override
  public Event getEventById(int eventId) throws CustomException {
    String sql = "SELECT * FROM Event WHERE eventId = ?";

    try {
      List<Event> ls = jdbcTemplate.query(sql, new EventMapper(), eventId);
      if (ls.size() == 0) {
        return null;
      } else {
        return ls.get(0);
      }
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }
  }

  @Override
  public <T> List<Event> getEventByAttribute(String attributeName, T attributeValue) throws CustomException {
    String sql = "SELECT * FROM Event WHERE " + attributeName + " = ?";
    try {
      return jdbcTemplate.query(sql, new EventMapper(), attributeValue);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }
  }
}

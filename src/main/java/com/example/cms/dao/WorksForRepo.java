package com.example.cms.dao;

import com.example.cms.Models.WorksFor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WorksForRepo implements WorksForDAO {
  private final JdbcTemplate jdbcTemplate;

  public WorksForRepo(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public WorksFor addWorksFor(WorksFor newWorksFor) throws CustomException {
    String sql = "INSERT INTO WorksFor(staffId, eventId) VALUES(?, ?)";
    int isCreated;
    try {
      isCreated = jdbcTemplate.update(sql, newWorksFor.getStaffId(), newWorksFor.getEventId());
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }
    if (isCreated == 0) {
      throw new CustomException("Could not insert event for staff to work at");
    }
    return newWorksFor;
  }

  @Override
  public Boolean deleteWorksFor(int staffId, int eventId) throws CustomException {
    String sql = "DELETE FROM WorksFor WHERE staffId = ? AND eventId = ?";
    int isDeleted;
    try {
      isDeleted = jdbcTemplate.update(sql, staffId, eventId);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }
    return isDeleted != 0;
  }

  @Override
  public List<Integer> getAllWorkersByEvent(int eventId) {
    String sql = "SELECT staffId FROM WorksFor WHERE eventId = ?";
    return jdbcTemplate.queryForList(sql, Integer.class, eventId);
  }

  @Override
  public List<Integer> getEventsByStaffId(int staffId) {
    String sql = "SELECT eventId FROM WorksFor WHERE staffId = ?";
    return jdbcTemplate.queryForList(sql, Integer.class, staffId);
  }

  @Override
  public void assignWork(int eventId, int groupNo) {
    String sql = "INSERT INTO WorksFor(staffId, eventId) " +
        "SELECT s.staffId, e.eventId FROM Staff s, Event e " +
        "WHERE e.eventId = ? AND s.venueId = (SELECT t.venueId FROM TakesPlace t WHERE t.eventId = e.eventId) " +
        "AND s.groupNumber = ? AND s.role LIKE 'STAFF'";
    jdbcTemplate.update(sql, eventId, groupNo);
  }

  @Override
  public Integer addWorksForAllStaffGno(Map<String, Integer> staff) throws CustomException {
    String sql = "INSERT INTO WorksFor (staffId , eventId)" +
        " SELECT s.staffId , e.eventId FROM Staff as s , Event as e" +
        " WHERE e.eventId = ? AND s.venueId = ? AND s.groupNumber = ?;";

    int isCreated = jdbcTemplate.update(sql, staff.get("eventId"), staff.get("venueId"), staff.get("groupNumber"));

    if (isCreated == 0) {
      throw new CustomException("Could not insert group Number staff");
    }
    return isCreated;
  }
}

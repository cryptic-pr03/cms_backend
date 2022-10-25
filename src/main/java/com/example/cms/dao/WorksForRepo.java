package com.example.cms.dao;


import com.example.cms.Models.WorksFor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WorksForRepo implements WorksForDAO {
    private final JdbcTemplate jdbcTemplate;

    public WorksForRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class WorksForMapper implements RowMapper<WorksFor> {
        @Override
        public WorksFor mapRow(ResultSet rs, int rowNum) throws SQLException {
            WorksFor worksFor = new WorksFor();
            worksFor.setStaffId(rs.getInt("staffId"));
            worksFor.setEventId(rs.getInt("eventId"));
            return worksFor;
        }
    }

    @Override
    public WorksFor addWorksFor(WorksFor newWorksFor) throws CustomException {
        String sql = "INSERT INTO WorksFor(staffId, eventId) VALUES(?, ?)";
        int isCreated;
        try {
            isCreated = jdbcTemplate.update(sql, newWorksFor.getStaffId(), newWorksFor.getEventId());
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        if(isCreated == 0) {
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
        }
        catch (Exception e) {
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
}

package com.example.cms.dao;

import com.example.cms.Models.Slot;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class SlotRepo implements SlotDAO {
    private static class SlotRepoMapper implements RowMapper<Slot> {

        @Override
        public Slot mapRow(ResultSet rs, int rowNum) throws SQLException {
            Slot slot = new Slot();
            slot.setSlotId(rs.getInt("slotId"));
            slot.setVenueId(rs.getInt("venueId"));
            slot.setStartTime(rs.getTime("startTime"));
            slot.setEndTime(rs.getTime("endTime"));
            slot.setSlotDate(rs.getDate("slotDate"));
            slot.setPrice(rs.getInt("price"));
            slot.setRented(rs.getBoolean("isRented"));
            return slot;
        }
    }

    private final JdbcTemplate jdbcTemplate;

    public SlotRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Slot addSlot(Slot newSlot) throws CustomException {
        String querySQL = "INSERT INTO Slot (slotId, venueId , slotDate , startTime , endTime , price , isRented) VALUES (?,?,?,?,?,?,?)";
//
        //
        int isCreated;
        try {
            isCreated = jdbcTemplate.update(querySQL, newSlot.getSlotId(), newSlot.getVenueId(), newSlot.getSlotDate(), newSlot.getStartTime(), newSlot.getEndTime(), newSlot.getPrice(), newSlot.getIsRented());
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        if (isCreated == 0) throw new CustomException("Cannot insert Slot");
        return newSlot;
    }

    @Override
    public Slot updateSlot(Slot newSlot, int venueId, int slotId, Date slotDate) throws CustomException {
        String querySQL = "UPDATE Slot SET startTime =?, endTime =? ,price=? , isRented=? WHERE slotId=? AND venueId = ? AND slotDate =?";
        int isUpdated;
        try {
            isUpdated = jdbcTemplate.update(querySQL, newSlot.getStartTime(), newSlot.getEndTime(), newSlot.getPrice(), newSlot.getIsRented(), slotId, venueId, slotDate);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        if (isUpdated == 0) throw new CustomException("Could not Update");
        return newSlot;
    }

    @Override
    public Boolean deleteSlot(int venueId, int slotId, Date slotDate) throws CustomException {

        String querySQL = "DELETE FROM Slot WHERE slotId = ? AND venueId = ? AND slotDate = ?";
        int isDeleted;
        try {
            isDeleted = jdbcTemplate.update(querySQL, slotId, venueId, slotDate);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        return isDeleted != 0;
    }

    @Override
    public <T> List<Slot> getSlotByAttribute(String attributeName, T attributeValue) throws CustomException {
        String querySQL = "SELECT * FROM Slot WHERE " + attributeName + "= ?";
        try {
            List<Slot> ls = jdbcTemplate.query(querySQL, new SlotRepoMapper(), attributeValue);
            return ls;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

    @Override
    public List<Map<String, Object>> getAllSlots() {
        String sql = "SELECT * FROM Slot AS s, Venue as v WHERE s.venueId = v.venueId";
        return jdbcTemplate.queryForList(sql);
    }
}

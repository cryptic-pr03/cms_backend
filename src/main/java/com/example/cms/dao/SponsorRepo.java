package com.example.cms.dao;

import com.example.cms.Models.Event;
import com.example.cms.Models.Pic;
import com.example.cms.Models.Sponsor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SponsorRepo implements SponsorDAO {
    private final JdbcTemplate jdbcTemplate;

    public SponsorRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class SponsorMapper implements RowMapper<Sponsor> {
        @Override
        public Sponsor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sponsor sponsor = new Sponsor();
            sponsor.setEventId(rs.getInt("eventId"));
            sponsor.setSponsorName(rs.getString("sponsorName"));
            return sponsor;
        }
    }

    @Override
    public Sponsor addSponsor(Sponsor newSponsor) throws CustomException {
        String sql =
                "INSERT INTO Sponsor(eventId, sponsorName) VALUES(?,?)";

        int isCreated = jdbcTemplate.update(sql, newSponsor.getEventId(), newSponsor.getSponsorName());

        if(isCreated == 0){
            throw new CustomException("Could not insert sponsor");
        }
        return newSponsor;
    }

    @Override
    public Boolean deleteSponsor(int eventId, String sponsorName) throws CustomException {
        String sql = "DELETE FROM Sponsor WHERE eventId = ? AND sponsorName = ?";

        int isDeleted;
        try{
            isDeleted = jdbcTemplate.update(sql, eventId, sponsorName);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        return isDeleted != 0;
    }

    @Override
    public List<Sponsor> getSponsorsByEventId(int eventId)
    {
        String sql = "SELECT * FROM Sponsor WHERE eventId = ? ";
            return jdbcTemplate.query(sql, new SponsorRepo.SponsorMapper(), eventId);

    }


}

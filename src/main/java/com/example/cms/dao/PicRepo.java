package com.example.cms.dao;

import com.example.cms.Models.Pic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PicRepo implements PicDAO
{
    private static class PicMapper implements RowMapper<Pic> {
        @Override
        public Pic mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pic pic = new Pic();
            pic.setVenueId(rs.getInt("venueId"));
            pic.setVenuePicUrl(rs.getString("venuePicUrl"));
            return pic;
        }
    }

    private final JdbcTemplate jdbcTemplate;

    public PicRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pic addPic(Pic newPic) throws CustomException {
        String sql =
                "INSERT INTO Pic(venueId, venuePicUrl) VALUES(?,?)";

        int isCreated = jdbcTemplate.update(sql, newPic.getVenueId(), newPic.getVenuePicUrl());

        if(isCreated == 0){
            throw new CustomException("Could not insert pic");
        }
        return newPic;
    }

    @Override
    public Boolean deletePic(String venuePicUrl, int venueId) throws CustomException {
        String sql = "DELETE FROM Pic WHERE venuePicUrl = ? AND venueId = ?";

        int isDeleted;
        try{
            isDeleted = jdbcTemplate.update(sql, venuePicUrl, venueId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        return isDeleted != 0;
    }

    @Override
    public List<Pic> getPicsByVenueId(int venueId) throws CustomException
    {
        String sql = "SELECT * FROM Pic WHERE venueId = ?";

        try
        {
            return jdbcTemplate.query(sql, new PicRepo.PicMapper(), venueId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }





}

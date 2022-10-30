package com.example.cms.dao;

import com.example.cms.Models.Venue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class VenueRepo implements VenueDAO{

    JdbcTemplate jdbcTemplate;

    public VenueRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class VenueMapper implements RowMapper<Venue> {
        @Override
        public Venue mapRow(ResultSet rs, int rowNum) throws SQLException {
            Venue venue = new Venue();
            venue.setVenueId(rs.getInt("venueId"));
            venue.setName(rs.getString("name"));
            venue.setCapacity(rs.getInt("capacity"));
            venue.setCity(rs.getString("city"));
            venue.setLandmark(rs.getString("landmark"));
            venue.setState(rs.getString("state"));
            venue.setAvailable(rs.getBoolean("isFunctional"));
            venue.setSeatMatrixDescription(rs.getString("seatMatrixDescription"));
            venue.setPicSeatMatrixUrl(rs.getString("picSeatMatrixUrl"));
            return venue;
        }
    }

    @Override
    public Venue addVenue(Venue newVenue) throws CustomException {
        String sql =
                "INSERT INTO Venue(name, capacity, city, landmark, state, " +
                "isFunctional, seatMatrixDescription, picSeatMatrixUrl) VALUES(?,?,?,?,?,?,?,?)";

        int isCreated;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {

            isCreated = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, newVenue.getName());
                ps.setInt(2, newVenue.getCapacity());
                ps.setString(3, newVenue.getCity());
                ps.setString(4, newVenue.getLandmark());
                ps.setString(5, newVenue.getState());
                ps.setBoolean(6, newVenue.isFunctional());
                ps.setString(7, newVenue.getSeatMatrixDescription());
                ps.setString(8, newVenue.getPicSeatMatrixUrl());

                return ps;
            }, keyHolder);

        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isCreated == 0 || keyHolder.getKey() == null){
            throw new CustomException("Venue not inserted");
        }

        newVenue.setVenueId(keyHolder.getKey().intValue());
        return newVenue;
    }

    @Override
    public Venue updateVenue(int venueId, Venue updatedVenue) throws CustomException {
        String sql = "UPDATE Venue " +
                     "SET name = ?, capacity = ?, city = ?, landmark = ?, state = ?, isAvailable = ?, seatMatrixDescription = ?, picSeatMatrixUrl = ? " +
                     "WHERE venueId = ?";

        int isUpdated;
        try {
            isUpdated = jdbcTemplate.update(sql, updatedVenue.getName(), updatedVenue.getCapacity(), updatedVenue.getCity(), updatedVenue.getLandmark(),
                    updatedVenue.getState(), updatedVenue.isFunctional(), updatedVenue.getSeatMatrixDescription(), updatedVenue.getPicSeatMatrixUrl(), venueId);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isUpdated == 0){
            throw new CustomException("Could not update venue with id " + venueId);
        }
        return updatedVenue;
    }

    @Override
    public Boolean deleteVenue(int venueId) throws CustomException {
        String sql = "DELETE FROM Venue WHERE venueId = ?";

        int isDeleted;
        try{
            isDeleted = jdbcTemplate.update(sql, venueId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        return isDeleted != 0;
    }

    @Override
    public List<Venue> getAllVenues() {
        String sql = "SELECT * FROM Venue";
        return jdbcTemplate.query(sql, new VenueMapper());
    }

    @Override
    public Venue getVenueById(int venueId) throws CustomException {
        String sql = "SELECT * FROM Venue WHERE venueId = ?";

        try{
            List<Venue> ls = jdbcTemplate.query(sql, new VenueMapper(), venueId);
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
    public <T> List<Venue> getVenueByAttribute(String attributeName, T attributeValue) throws CustomException {
        String sql = "SELECT * FROM Venue WHERE " + attributeName + " = ?";
        try{
            return jdbcTemplate.query(sql, new VenueMapper(), attributeValue);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}

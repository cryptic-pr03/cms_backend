package com.example.cms.dao;

import com.example.cms.Models.Venue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class VenueRepo implements VenueDAO {

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
      venue.setSilverSeats(rs.getInt("silverSeats"));
      venue.setGoldSeats(rs.getInt("goldSeats"));
      venue.setPlatinumSeats(rs.getInt("platinumSeats"));
      venue.setCity(rs.getString("city"));
      venue.setLandmark(rs.getString("landmark"));
      venue.setState(rs.getString("state"));
      venue.setFunctional(rs.getBoolean("isFunctional"));
      venue.setPicSeatMatrixUrl(rs.getString("picSeatMatrixUrl"));
      return venue;
    }
  }

  @Override
  public Venue addVenue(Venue newVenue) throws CustomException {
    String sql = "INSERT INTO Venue(name, silverSeats, goldSeats, platinumSeats, city, landmark, state, " +
        "isFunctional, picSeatMatrixUrl) VALUES(?,?,?,?,?,?,?,?,?)";

    int isCreated;
    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    try {

      isCreated = jdbcTemplate.update(con -> {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, newVenue.getName());
        ps.setInt(2, newVenue.getSilverSeats());
        ps.setInt(3, newVenue.getGoldSeats());
        ps.setInt(4, newVenue.getPlatinumSeats());
        ps.setString(5, newVenue.getCity());
        ps.setString(6, newVenue.getLandmark());
        ps.setString(7, newVenue.getState());
        ps.setBoolean(8, newVenue.isFunctional());
        ps.setString(9, newVenue.getPicSeatMatrixUrl());

        return ps;
      }, keyHolder);

    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }

    if (isCreated == 0 || keyHolder.getKey() == null) {
      throw new CustomException("Venue not inserted");
    }

    newVenue.setVenueId(keyHolder.getKey().intValue());
    return newVenue;
  }

  @Override
  public Venue updateVenue(int venueId, Venue updatedVenue) throws CustomException {
    String sql = "UPDATE Venue " +
        "SET name = ?, silverSeats= ?, goldSeats=?, platinumSeats=?, city = ?, landmark = ?, state = ?, isFunctional = ?, picSeatMatrixUrl = ? "
        +
        "WHERE venueId = ?";

    int isUpdated;
    try {
      isUpdated = jdbcTemplate.update(sql, updatedVenue.getName(), updatedVenue.getSilverSeats(),
          updatedVenue.getGoldSeats(), updatedVenue.getPlatinumSeats(), updatedVenue.getCity(),
          updatedVenue.getLandmark(),
          updatedVenue.getState(), updatedVenue.isFunctional(), updatedVenue.getPicSeatMatrixUrl(), venueId);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }

    if (isUpdated == 0) {
      throw new CustomException("Could not update venue with id " + venueId);
    }
    return updatedVenue;
  }

  @Override
  public Boolean deleteVenue(int venueId) throws CustomException {
    String sql = "DELETE FROM Venue WHERE venueId = ?";

    int isDeleted;
    try {
      isDeleted = jdbcTemplate.update(sql, venueId);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }

    return isDeleted != 0;
  }

  @Override
  public List<Venue> getAllVenues() {
    List ls = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList();
    String sql = "SELECT * FROM Venue";
    if (ls.contains("ARTIST_MANAGER"))
      sql = sql + " WHERE isFunctional = TRUE";
    return jdbcTemplate.query(sql, new VenueMapper());
  }

  @Override
  public Venue getVenueById(int venueId) throws CustomException {
    String sql = "SELECT * FROM Venue WHERE venueId = ?";

    try {
      List<Venue> ls = jdbcTemplate.query(sql, new VenueMapper(), venueId);
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
  public <T> List<Venue> getVenueByAttribute(String attributeName, T attributeValue) throws CustomException {
    String sql = "SELECT * FROM Venue WHERE " + attributeName + " = ?";
    try {
      return jdbcTemplate.query(sql, new VenueMapper(), attributeValue);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }
  }

  @Override
  public Map<String, Object> getVenueAndSeats(int venueId) {
    String sql = "SELECT * FROM Venue WHERE venueId = ?";

    Map<String, Object> res = jdbcTemplate.queryForMap(sql, venueId);

    res.put("seats", jdbcTemplate.queryForList("SELECT * FROM Seat WHERE venueId = ?", venueId));
    return res;
  }
}

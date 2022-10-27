package com.example.cms.dao;

import com.example.cms.Models.Seat;
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
public class SeatRepo implements SeatDAO{
    private static class SeatRepoMapper implements RowMapper<Seat>{

        @Override
        public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
            Seat seat = new Seat();
            seat.setSeatId(rs.getInt("seatId"));
            seat.setseatType(rs.getString("seatType"));
            seat.setVenueId(rs.getInt("venueId"));
            return seat;
        }
    }

    private final JdbcTemplate jdbcTemplate;
    public SeatRepo(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate ;}

    @Override
    public Seat getSeat(int SeatId, int venueId) throws CustomException {
        String querySQL = "SELECT * FROM seat WHERE seatId=? AND venueId=?";
        try{
            List<Seat> ls = jdbcTemplate.query(querySQL , new SeatRepoMapper(), SeatId , venueId);
            if(ls.size() == 0){
                throw new CustomException("No seat with Seat and Venue");
            }else{
                return ls.get(0);
            }
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Seat addSeat(Seat newSeat) throws CustomException {
        String querySQL = "INSERT INTO seat (seatId, venueId , seatType) VALUES(?,?,?)";
        int isCreated ;
        try{
            isCreated = jdbcTemplate.update(querySQL , newSeat.getSeatId() , newSeat.getVenueId(),newSeat.getseatType());
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isCreated == 0){
            throw new CustomException("Seat can not be added"+isCreated);
        }
        return newSeat;
    }

    @Override
    public Seat updateSeat(int SeatId, int venueId, Seat updatedSeat) throws CustomException {
        String querySQL = "UPDATE Seat SET seatType=? WHERE seatId = ? AND venueId=?";
        int isUpdated ;
        try{
            isUpdated = jdbcTemplate.update(querySQL , updatedSeat.getseatType() , SeatId , venueId);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isUpdated == 0)throw new CustomException("Can not update");
        return updatedSeat;
    }

    @Override
    public Boolean deleteSeat(int SeatId, int venueId) throws CustomException {
        String querySQL = "DELETE FROM Seat WHERE seatId=? AND venueId=?";
        int isDeleted;
        try{
            isDeleted = jdbcTemplate.update(querySQL , SeatId , venueId);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        return isDeleted!=0;
    }

    @Override
    public List<Seat> getSeatByVenueId(int venueId) throws CustomException {
        String querySQL = "SELECT * FROM Seat WHERE venueId=?";
        try {
            List<Seat> ls = jdbcTemplate.query(querySQL, new SeatRepoMapper(), venueId);
            return ls;
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Seat> getSeatAtVenueByType(int venueId, String seatType) throws CustomException {
        String querySQL = "SELECT * FROM Seat WHERE venueId=? AND seatType=?";
        try {
            List<Seat> ls = jdbcTemplate.query(querySQL, new SeatRepoMapper(), venueId, seatType);
            return ls;
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}

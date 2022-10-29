package com.example.cms.dao;

import com.example.cms.Models.SeatBook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SeatBookRepo implements SeatBookDAO {
    private static class SeatBookRepoMapper implements RowMapper<SeatBook>{

        @Override
        public SeatBook mapRow(ResultSet rs, int rowNum) throws SQLException {
            SeatBook seatBook = new SeatBook();
            seatBook.setSeatId(rs.getInt("seatId"));
            seatBook.seteventId(rs.getInt("eventId"));
            seatBook.setTransactionId(rs.getInt("transactionId"));
            return seatBook;
        }
    }

    private final JdbcTemplate jdbcTemplate;
    public SeatBookRepo(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public SeatBook addSeatBook(SeatBook newSeatBook) throws CustomException {
        String querySQL = "INSERT INTO SeatBook (eventId,seatId,transactionId) VALUES (?,?,?)";
        int isCreated;
        try{
            isCreated = jdbcTemplate.update(querySQL , newSeatBook.geteventId(), newSeatBook.getSeatId(),newSeatBook.getTransactionId());
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isCreated == 0)throw new CustomException("Can not insert SeatBooking Entry!");
        return newSeatBook;
    }

    @Override
    public int getTrxnId(int eventId, int seatId) throws CustomException {
        String querySQL = "SELECT * FROM SeatBook WHERE eventId=? AND seatId=?";
        try{
            List<SeatBook> ls = jdbcTemplate.query(querySQL , new SeatBookRepoMapper() , eventId , seatId);
            if(ls.size() == 0){
                throw new CustomException("No transaction Done for this seat");
            }else{
                return ls.get(0).getTransactionId();
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}

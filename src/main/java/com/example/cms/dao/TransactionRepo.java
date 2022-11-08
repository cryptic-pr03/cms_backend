package com.example.cms.dao;


import com.example.cms.Constants;
import com.example.cms.Models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class TransactionRepo implements TransactionDAO {
    private final JdbcTemplate jdbcTemplate;

    public TransactionRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(rs.getString("transactionId"));
            transaction.setDate(rs.getDate("date"));
            transaction.setTime(rs.getTime("time"));
            transaction.setType(rs.getString("type"));
            transaction.setAmount(rs.getInt("amount"));
            transaction.setUserId(rs.getInt("userId"));
            transaction.setEventId(rs.getInt("eventId"));
            transaction.settransactionImageUrl(rs.getString("transactionImageUrl"));
            return transaction;
        }
    }
    @Override
    public Transaction addTrxn(Transaction newTransaction) throws CustomException, ParseException {
        String sql = "INSERT INTO Transaction VALUES(?, ?, ?, ?, ?, ?, NULL, ?)";
        int isCreated;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String dateStr = sdf.format(date);
        java.sql.Time currTime = new java.sql.Time(sdf.parse(dateStr).getTime());
        java.sql.Date currDate = new java.sql.Date(sdf.parse(dateStr).getTime());
        try {
            newTransaction.setTransactionId(Constants.getRandomString(30));
            newTransaction.setDate(currDate);
            newTransaction.setTime(currTime);

            isCreated = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, newTransaction.getTransactionId());
                ps.setDate(2, newTransaction.getDate());
                ps.setTime(3, newTransaction.getTime());
                ps.setString(4, newTransaction.getType());
                ps.setInt(5, newTransaction.getAmount());
                ps.setInt(6, newTransaction.getUserId());
                ps.setString(7, newTransaction.gettransactionImageUrl());
                return ps;
            });
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        if(isCreated == 0){
            throw new CustomException("Could not add transaction");
        }
        return newTransaction;
    }
//    {
//        "id": 5,
//            "seatId": 6,
//            "eventId": 26,
//            "isBooked": false,
//            "price": 200,
//            "seatType": "Platinum"
//    }
    @Override
    public void addEventIdForTrxn(String transactionId, int eventId) {
        String sql =
                "UPDATE Transaction SET eventId = ? " +
                "WHERE transactionId = ?";
//        System.out.println("here2 " + transactionId + ' ' + eventId);
        jdbcTemplate.update(sql, eventId, transactionId);
    }

    @Override
    public List<Transaction> getTrxnsByUserId(int userId) {
        String sql = "SELECT * FROM Transaction WHERE userId = ?";
        return jdbcTemplate.query(sql, new TransactionMapper(), userId);
    }

    @Override
    public List<Transaction> getTrxnsByEventId(int eventId) {
        String sql = "SELECT * FROM Transaction WHERE eventId = ?";
        return jdbcTemplate.query(sql, new TransactionMapper(), eventId);
    }
}

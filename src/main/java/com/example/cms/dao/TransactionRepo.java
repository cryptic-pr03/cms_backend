package com.example.cms.dao;


import com.example.cms.Models.Transaction;
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
public class TransactionRepo implements TransactionDAO {
    private final JdbcTemplate jdbcTemplate;

    public TransactionRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(rs.getInt("transactionId"));
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
    public Transaction addTrxn(Transaction newTransaction) throws CustomException {
        String sql = "INSERT INTO Transaction(date, time, type, amount, userId, eventId, transactionImageUrl) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int isCreated;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            isCreated = jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setDate(1, newTransaction.getDate());
                ps.setTime(2, newTransaction.getTime());
                ps.setString(3, newTransaction.getType());
                ps.setInt(4, newTransaction.getAmount());
                ps.setInt(5, newTransaction.getUserId());
                ps.setInt(6, newTransaction.getEventId());
                ps.setString(7, newTransaction.gettransactionImageUrl());
                return ps;
            }, keyHolder);
            newTransaction.setTransactionId(keyHolder.getKey().intValue());
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        if(isCreated == 0 || keyHolder.getKey() == null){
            throw new CustomException("Could not add transaction");
        }
        return newTransaction;
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

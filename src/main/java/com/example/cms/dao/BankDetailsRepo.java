package com.example.cms.dao;

import com.example.cms.Models.BankDetails;
import com.example.cms.Models.Event;
import com.example.cms.Models.Pic;
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
public class BankDetailsRepo implements BankDetailsDAO{

    private final JdbcTemplate jdbcTemplate;

    public BankDetailsRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class BankDetailsMapper implements RowMapper<BankDetails> {
        @Override
        public BankDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            BankDetails bankDetails = new BankDetails();
            bankDetails.setAccountNo(rs.getInt("accountNo"));
            bankDetails.setIfscCode(rs.getInt("IfscCode"));
            bankDetails.setBankName(rs.getString("bankName"));
            bankDetails.setBranchName(rs.getString("branchName"));
            bankDetails.setUserId(rs.getInt("userId"));
            return bankDetails;
        }
    }

    @Override
    public BankDetails addBankDetails(BankDetails newBankDetails) throws CustomException {
        String sql = "INSERT INTO BankDetails(accountNo, IfscCode, bankName, branchName, userId) VALUES(?,?,?,?,?)";


            int isCreated = jdbcTemplate.update(sql, newBankDetails.getAccountNo(), newBankDetails.getIfscCode(), newBankDetails.getBankName(), newBankDetails.getBranchName(),newBankDetails.getUserId());

            if(isCreated == 0){
                throw new CustomException("Could not insert bank details");
            }
            return newBankDetails;
    }

    @Override
    public Boolean deleteBankDetails(int accountNo) throws CustomException {
        String sql = "DELETE FROM BankDetails WHERE accountNo = ?";

        int isDeleted;
        try{
            isDeleted = jdbcTemplate.update(sql, accountNo);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        return isDeleted != 0;
    }

    @Override
    public List<BankDetails> getBankDetailsByUserId(int userId) throws CustomException {
        String sql = "SELECT * FROM BankDetails WHERE userId = ?";

        try{
            return jdbcTemplate.query(sql, new BankDetailsRepo.BankDetailsMapper(), userId);

        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public <T> List<BankDetails> getBankDetailsByAttribute(String attributeName, T attributeValue)
    {
        String sql = "SELECT * FROM BankDetails WHERE " + attributeName + " = ?";

            return jdbcTemplate.query(sql, new BankDetailsMapper(), attributeValue);

    }


}

package com.example.cms.dao;

import com.example.cms.Models.Event;
import com.example.cms.Models.Salary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
@Repository
public class SalaryRepo implements SalaryDAO{
    private final JdbcTemplate jdbcTemplate;

    public SalaryRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class SalaryMapper implements RowMapper<Salary> {
        @Override
        public Salary mapRow(ResultSet rs, int rowNum) throws SQLException {
            Salary salary = new Salary();
            salary.setStaffId(rs.getInt("staffId"));
            salary.setTimeOfPayment(rs.getTimestamp("timeOfPayment"));
            salary.setAmount(rs.getInt("amount"));
            salary.setBonus(rs.getInt("bonus"));
            salary.setPaidStatus(rs.getBoolean("paidStatus"));
            return salary;
        }
    }
    @Override
    public Salary addSalary(Salary newSalary) throws CustomException {
        String sql =
                "INSERT INTO Salary(staffId, timeOfPayment, amount, bonus, paidStatus) " +
                        "VALUES(?,?,?,?,?)";
        int isCreated;

        isCreated = jdbcTemplate.update(sql, newSalary.getStaffId(), newSalary.getTimeOfPayment(),
                newSalary.getAmount(), newSalary.getBonus(),
                newSalary.getPaidStatus());

        if(isCreated == 0){
            throw new CustomException("Could not insert salary of the staff");
        }
        return newSalary;
    }

    @Override
    public Salary changePaidStatus(int staffId, Timestamp timeOfPayment, Salary changedPaidStatus) throws CustomException {
        String sql =
                "UPDATE Salary " +
                        "SET paidStatus = ? " +
                        "WHERE staffId = ? AND timeOfPayment = ?";

        int isUpdated = jdbcTemplate.update(sql, changedPaidStatus.getPaidStatus(), staffId, timeOfPayment);

        if(isUpdated == 0){
            throw new CustomException("Could not change paid status of the staff");
        }
        return changedPaidStatus;
    }



    @Override
    public List<Salary> getAllSalariesOfStaff(int staffId) throws CustomException {
        String sql = "SELECT * FROM Salary WHERE staffId = ?";

        try{
            return jdbcTemplate.query(sql, new SalaryMapper(), staffId);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}

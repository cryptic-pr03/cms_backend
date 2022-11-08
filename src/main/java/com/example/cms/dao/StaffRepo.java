package com.example.cms.dao;

import com.example.cms.Models.Staff;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class StaffRepo implements StaffDAO{
    private static class StaffRepoMapper implements RowMapper<Staff>{

        @Override
        public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
            Staff staff = new Staff();
            staff.setStaffId(rs.getInt("staffId"));
            staff.setFirstName(rs.getString("firstName"));
            staff.setLastName(rs.getString("lastName"));
            staff.setEmail(rs.getString("email"));
            staff.setPassword(rs.getString("password"));
            staff.setContactNo(rs.getString("contactNo"));
            staff.setGender(rs.getString("gender"));
            staff.setDOB(rs.getDate("DOB"));
            staff.setRole(rs.getInt("role"));
            staff.setGroupNumber(rs.getInt("groupNumber"));
            staff.setSalary(rs.getInt("salary"));
            staff.setVenueId(rs.getInt("venueId"));
            staff.setJoiningDate(rs.getDate("joiningDate"));
            staff.setLeavingDate(rs.getDate("leavingDate"));
            staff.setAccountNo(rs.getString("accountNo"));
            staff.setIFSCCode(rs.getString("IFSCCode"));
            return staff;

        }
    }
    private final JdbcTemplate jdbcTemplate;
    public StaffRepo(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Staff addStaff(Staff newStaff) throws CustomException {
        //null  bank name, branch name
        String querySQL = "INSERT INTO Staff ( firstName, lastName,email ,contactNo,gender,DOB,role,groupNumber,salary,venueId,joiningDate,leavingDate,accountNo,IFSCCode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int isCreated ;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        try{
            isCreated = jdbcTemplate.update(con ->{
                PreparedStatement ps = con.prepareStatement(querySQL , Statement.RETURN_GENERATED_KEYS);
                ps.setString(1 , newStaff.getFirstName());
                ps.setString(2 , newStaff.getLastName());
                ps.setString(3 , newStaff.getEmail());
                ps.setString(4 , newStaff.getContactNo());
                ps.setString(5 , newStaff.getGender());
                ps.setDate(6,newStaff.getDOB());
                ps.setInt(7,newStaff.getRole());
                ps.setInt(8,newStaff.getGroupNumber());
                ps.setInt(9,newStaff.getSalary());
                ps.setInt(10,newStaff.getVenueId());
                ps.setDate(11,newStaff.getJoiningDate());
                ps.setDate(12,newStaff.getLeavingDate());
                ps.setString(13,newStaff.getAccountNo());
                ps.setString(14,newStaff.getIFSCCode());

                return ps;
            } , keyHolder);
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isCreated == 0 || keyHolder.getKey() == null) throw new CustomException("Staff not inserted");

        newStaff.setStaffId(keyHolder.getKey().intValue());
        return newStaff;
    }

    @Override
    public Staff updateStaff(Staff updatedStaff) throws CustomException {
        String querySQL = "UPDATE Staff SET firstName =?, lastName=?,email=? ,password=? ,contactNo=?,gender=?,DOB=?,role=?,groupNumber=?,salary=?,venueId=?,joiningDate=?,leavingDate=?,accountNo=?,IFSCCode=? WHERE staffId=?";
        int isUpdated;
        try{
            isUpdated = jdbcTemplate.update(querySQL , updatedStaff.getFirstName() ,updatedStaff.getLastName() , updatedStaff.getEmail() , updatedStaff.getPassword() , updatedStaff.getContactNo() ,updatedStaff.getGender(),
                    updatedStaff.getDOB() , updatedStaff.getRole() , updatedStaff.getGroupNumber() , updatedStaff.getSalary() , updatedStaff.getVenueId() ,updatedStaff.getJoiningDate() ,updatedStaff.getLeavingDate() , updatedStaff.getAccountNo() , updatedStaff.getIFSCCode() , updatedStaff.getStaffId());
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

        if(isUpdated == 0)throw new CustomException("Can not update Staff");
        return getStaffById(updatedStaff.getStaffId());
    }

    @Override
    public Boolean delete(int staffId) throws CustomException {
        String querySQL="DELETE FROM Staff WHERE staffId=?";
        int isDeleted;
        try{
            isDeleted = jdbcTemplate.update(querySQL , staffId);
        }catch (Exception e){
            throw new CustomException("Can not Delete");
        }
        return isDeleted != 0;
    }

    @Override
    public Staff getStaffById(int StaffId) throws CustomException {
        String querySQL = "SELECT * FROM Staff WHERE staffId = ?";
        try{
            List<Staff> ls = jdbcTemplate.query(querySQL , new StaffRepoMapper() , StaffId);
            if (ls.size() == 0) {
                return null;
            }
            else {
                return ls.get(0);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

    }

    @Override
    public Staff getStaffByEmailId(String StaffEmailId) throws CustomException {
        String querySQL = "SELECT * FROM Staff WHERE email = ?";
        try{
            List<Staff> ls = jdbcTemplate.query(querySQL , new StaffRepoMapper() , StaffEmailId);
            if (ls.size() == 0) {
                return null;
            }
            else return ls.get(0);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public <T> List<Staff> getStaffByAttribute(String attributeName, T attributeValue) throws CustomException {
        String querySQL = "SELECT * FROM Staff WHERE " + attributeName + " = ?";

        try{
            List<Staff> ls = jdbcTemplate.query(querySQL , new StaffRepoMapper() , attributeValue);
            return ls;
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> getStaffByVenue(int venueId) {
        String sql = "SELECT * FROM Staff WHERE venueId = ?";

        List<Map<String, Object>> ls = jdbcTemplate.queryForList(sql, venueId);

        for(Map<String, Object> obj : ls){
            obj.remove("password");
        }

        return ls;
    }


    @Override
    public List<Map<String, Object>> getSchedule(int staffId) {
        String sql =
                "SELECT * FROM Event e, WorksFor w WHERE w.staffId = ? AND e.eventId = w.eventId " +
                "ORDER BY e.eventDate, e.startTime";
        return jdbcTemplate.queryForList(sql, staffId);
    }
}

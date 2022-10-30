package com.example.cms.dao;

import com.example.cms.Models.Staff;

import java.util.List;

public interface StaffDAO {
    Staff addStaff (Staff  newStaff) throws CustomException;
    Staff updateStaff (Staff updatedStaff) throws CustomException;
    Boolean delete (int staffId) throws CustomException;
    Staff getStaffById(int StaffId) throws CustomException;
    <T> List<Staff> getStaffByAttribute(String attributeName, T attributeValue) throws CustomException;
}

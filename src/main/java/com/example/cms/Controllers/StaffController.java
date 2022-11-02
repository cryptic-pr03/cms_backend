package com.example.cms.Controllers;

import com.example.cms.Models.Staff;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.StaffDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffDAO staffDAO;

    @PostMapping
    @PreAuthorize("hasAuthority('VENUE_MANAGER') or hasAuthority('ADMIN')")
    public Staff addStaff(@RequestBody Staff staff) throws CustomException {
        try {
            return staffDAO.addStaff(staff);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PutMapping
    public Staff updateStaff(@RequestBody Staff staff) throws CustomException {
        try {
            return staffDAO.updateStaff(staff);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{staffId}")
    public Boolean delete(@PathVariable int staffId) throws CustomException {
        try {
            return staffDAO.delete(staffId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("id/{staffId}")
    public Staff getStaffById(@PathVariable int staffId) throws CustomException {
        try {
            return staffDAO.getStaffById(staffId);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("attribute/{attributeName}/{attributeValue}")
    public <T> List<Staff> getStaffByAttribute(@PathVariable String attributeName, @PathVariable T attributeValue) throws CustomException {
        try {
            List<Staff> ls = staffDAO.getStaffByAttribute(attributeName, attributeValue);
            return ls;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

}

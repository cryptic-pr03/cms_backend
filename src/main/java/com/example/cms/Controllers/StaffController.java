package com.example.cms.Controllers;

import com.example.cms.Models.Staff;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.StaffDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "http://localhost:3000")
public class StaffController {
    @Autowired
    private StaffDAO staffDAO;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('VENUE_MANAGER', 'ADMIN')")
    public Staff addStaff(@RequestBody Staff staff) throws CustomException {
        try {
            return staffDAO.addStaff(staff);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('STAFF', 'VENUE_MANAGER', 'ADMIN')")
    public Staff updateStaff(@RequestBody Staff staff) throws CustomException {
        try {
            return staffDAO.updateStaff(staff);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @DeleteMapping("/{staffId}")
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
            return staffDAO.getStaffByAttribute(attributeName, attributeValue);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/schedule/{staffId}")
    @PreAuthorize("hasAnyAuthority('STAFF', 'VENUE_MANAGER', 'ADMIN')")
    public ResponseEntity getSchedule(@PathVariable int staffId) {
        try {
            return ResponseEntity.ok(staffDAO.getSchedule(staffId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

package com.example.cms.Controllers;

import com.example.cms.Models.Staff;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.StaffDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = {"http://localhost:3000","http://cms-frontend-tau.vercel.app/"})
public class StaffController {
    @Autowired
    private StaffDAO staffDAO;

    @PostMapping
    public ResponseEntity<?> addStaff(@RequestBody Staff staff) throws CustomException {
//        List auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList();
//        System.out.println(auths);
//        if (auths.contains("ADMIN") || auths.contains("VENUE_MANAGER"))
        try {
            System.out.println(staff);
            Staff addedStaff = staffDAO.addStaff(staff);
            return ResponseEntity.ok(addedStaff);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
//            return ResponseEntity.internalServerError().body(e.getMessage());
        }
//        else return ResponseEntity.badRequest().build();
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

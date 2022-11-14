package com.example.cms.Controllers;

import com.example.cms.Models.Staff;
import com.example.cms.Models.User;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.StaffDAO;
import com.example.cms.dao.UserDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3000","http://cms-frontend-tau.vercel.app/"})
public class UserController {
    private final UserDAO userRepo;
    private final StaffDAO staffRepo;

    public UserController(UserDAO userRepo, StaffDAO staffRepo) {
        this.userRepo = userRepo;
        this.staffRepo = staffRepo;
    }


    @PostMapping("/profile")
    ResponseEntity<?> getUserDetails(@RequestBody Map<String, Object> req) {
        int id = (int) req.get("id");
        try {
            if ((int) req.get("typeUserCode") < 3) {
                User userData = userRepo.getUserById(id);
                userData.setPassword("");
                return ResponseEntity.ok(userData);

            } else {
                Staff staffData = staffRepo.getStaffById(id);
                staffData.setPassword("");
                return ResponseEntity.ok(staffData);
            }
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/seatBookings/{userId}")
    ResponseEntity<?> getSeatBookingsOfUser(@PathVariable int userId) {
        try {
            return ResponseEntity.ok(userRepo.getAllSeatBookingsOfUser(userId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}

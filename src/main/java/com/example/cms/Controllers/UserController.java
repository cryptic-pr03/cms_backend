package com.example.cms.Controllers;

import com.example.cms.Models.User;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.UserDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserDAO userRepo;

    public UserController(UserDAO userRepo) {
        this.userRepo = userRepo;
    }


    @GetMapping("/profile/{id}")
    ResponseEntity<?> getUserDetails(@PathVariable int id) {
        try {
            User userData = userRepo.getUserById(id);
            userData.setPassword(null);
            return ResponseEntity.ok(userData);
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/seatBookings/{userId}")
    ResponseEntity<?> getSeatBookingsOfUser(@PathVariable int userId){
        try{
            return ResponseEntity.ok(userRepo.getAllSeatBookingsOfUser(userId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}

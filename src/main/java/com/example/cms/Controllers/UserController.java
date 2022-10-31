package com.example.cms.Controllers;

import com.example.cms.Models.User;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.UserDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

}

package com.example.cms.Controllers;


import com.example.cms.Models.TypeUser;
import com.example.cms.Models.User;
import com.example.cms.Security.JwtUtil;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.TypeUserDAO;
import com.example.cms.dao.UserDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SecurityController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final UserDAO userRepo;
    private final TypeUserDAO typeUserRepo;
    private final PasswordEncoder passwordEncoder;

    public SecurityController(JwtUtil jwtUtil, AuthenticationManager authManager, UserDAO userRepo, TypeUserDAO typeUserRepo, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.typeUserRepo = typeUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> Login(@RequestBody Map<String, Object> req) {
        System.out.print("login: ");
        System.out.println(req);

        String email = (String) req.get("email");
        String password = (String) req.get("password");
        int typeUserCode = (Integer) req.get("typeUserCode");

        try {
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(auth);

//        UserDetails obj = (UserDetails) auth.getPrincipal();
            User currentUser = userRepo.getUserByEmailId(email);
            List<Integer> roles = typeUserRepo.getRolesByUserId(currentUser.getUserID());
            boolean isPresent = roles.contains(typeUserCode);

            String token = jwtUtil.generateToken(auth, currentUser.getUserID(), typeUserCode);

//            System.out.println(auth.getPrincipal());
            Map<String, Object> returnObj = new HashMap<>();
            returnObj.put("token", token);
            returnObj.put("isPresent", isPresent);

            return ResponseEntity.ok(returnObj);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<?> Register(@RequestBody LinkedHashMap<String, Object> req) throws ParseException {
        System.out.println(req);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
//        java.sql.Date dob = ;

        User newUser = new User(
                (Integer) req.get("id"),
                (String) req.get("firstName"),
                (String) req.get("lastName"),
                (String) req.get("email"),
                (String) req.get("password"),
                (String) req.get("contactNo"),
                (String) req.get("gender"),
                new java.sql.Date(dateFormatter.parse((String) req.get("DOB")).getTime())
        );

        String reqUserEmail = newUser.getEmail();
        String reqUserPassword = newUser.getPassword();

        try {
            if (userRepo.getUserByEmailId(reqUserEmail) != null) {
                return ResponseEntity.badRequest().body("This Email is already in use!");
            } else {
                newUser.setPassword(passwordEncoder.encode(reqUserPassword));

                newUser = userRepo.addUser(newUser);

                TypeUser newTypeUser = new TypeUser(newUser.getUserID(), (Integer) req.get("typeUserCode"));
                typeUserRepo.addUserRole(newTypeUser);
                return ResponseEntity.ok("User Registration Successful");
            }
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @PostMapping("/log_out")
    public ResponseEntity<?> Logout() {
        System.out.println("logout");
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok().build();
    }
}

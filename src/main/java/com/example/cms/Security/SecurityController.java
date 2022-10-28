package com.example.cms.Security;


import com.example.cms.Models.User;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.UserDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SecurityController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final UserDAO userRepo;
    private final PasswordEncoder passwordEncoder;

    public SecurityController(JwtUtil jwtUtil, AuthenticationManager authManager, UserDAO userRepo, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity Login(@RequestBody Map<String, String> req) {

        String email = req.get("userEmail");
        String password = req.get("userPassword");
        String typeUser = req.get("typeUser");

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtUtil.generateToken(auth);

//        UserDetails obj = (UserDetails) auth.getPrincipal();

        System.out.println(auth.getPrincipal());

        Map<String, String> returnObj = new HashMap<>();
        returnObj.put("token", token);

        return ResponseEntity.ok(returnObj);
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity Register(@RequestBody User user){

        String reqUserEmail = user.getUserEmail();
        String reqUserPassword = user.getUserPassword();

        try {
            if(userRepo.getUserByEmailId(reqUserEmail) != null){
                return ResponseEntity.badRequest().body("This Email is already in use!");
            }
            else{
                user.setUserPassword(passwordEncoder.encode(reqUserPassword));

                userRepo.addUser(user);

                Map<String, String> Obj = new HashMap<>();
                Obj.put("userEmail", reqUserEmail);
                Obj.put("userPassword", reqUserPassword);

                return Login(Obj);
            }
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/log_out")
    public ResponseEntity Logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok().build();
    }
}

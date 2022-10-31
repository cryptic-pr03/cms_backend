package com.example.cms.Controllers;

import com.example.cms.Models.TypeUser;
import com.example.cms.Security.JwtUtil;
import com.example.cms.dao.CustomException;
import com.example.cms.dao.TypeUserDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TypeUserController {

    private final TypeUserDAO typeUserRepo;

    public TypeUserController(TypeUserDAO typeUserRepo, JwtUtil jwtUtil) {
        this.typeUserRepo = typeUserRepo;
    }

    @PostMapping(value = "/newTypeUser", consumes = "application/json")
    public ResponseEntity<?> addRole(@RequestBody TypeUser req) throws CustomException {
        System.out.println("addRole");
        System.out.println(req);
        try {
            typeUserRepo.addUserRole(req);
            return ResponseEntity.ok("User Role Added");
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}

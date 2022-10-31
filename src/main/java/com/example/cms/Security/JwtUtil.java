package com.example.cms.Security;

import com.example.cms.dao.TypeUserDAO;
import com.example.cms.dao.UserDAO;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final UserDAO userRepo;

    private final TypeUserDAO typeUserRepo;

    private final String jwtSecret = "DbmsProjectKey";

    public JwtUtil(UserDAO userRepo, TypeUserDAO typeUserRepo) {
        this.userRepo = userRepo;
        this.typeUserRepo = typeUserRepo;
    }

    public String generateToken(Authentication authentication, int userId, int typeUserCode) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        int jwtExpirationMs = 5 * 60 * 60;
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .claim("typeUserCode", typeUserCode)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }

        return false;
    }
}
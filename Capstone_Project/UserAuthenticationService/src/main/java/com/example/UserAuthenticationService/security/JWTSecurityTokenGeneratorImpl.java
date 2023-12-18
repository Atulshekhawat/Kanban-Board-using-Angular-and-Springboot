package com.example.UserAuthenticationService.security;

import com.example.UserAuthenticationService.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    public String createToken(User user){
        // Write logic to create the Jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getUserEmail());
        System.out.println(claims + " : " + user.getUserEmail());
        return generateToken(claims, user.getUserEmail());
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        // Generate the token and set the user id in the claims
        String jwtToken = Jwts.builder().setIssuer("shubham")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        System.out.println(jwtToken);
        return jwtToken;
    }
}

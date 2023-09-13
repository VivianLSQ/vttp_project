package sg.nus.edu.iss.vttp_project.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import sg.nus.edu.iss.vttp_project.config.JwtConfig;
import sg.nus.edu.iss.vttp_project.models.User;

@Service 
public class AuthService {

    //Using Json Web Token

    @Autowired
    private final JwtConfig jwtConfig;

    public AuthService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUsername());
        claims.put("roles", user.getRoles());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtConfig.getExpirationInMs());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(jwtConfig.jwtSecretKey(), jwtConfig.getAlgorithm())
                .compact();
    }


      public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtConfig.jwtSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

  
}

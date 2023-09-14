package sg.nus.edu.iss.vttp_project.services;


import java.util.Date;
import java.util.HashMap; 
import java.util.Map;
// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import sg.nus.edu.iss.vttp_project.config.JwtConfig;
import sg.nus.edu.iss.vttp_project.models.User;
import sg.nus.edu.iss.vttp_project.models.UserPrincipal;
import sg.nus.edu.iss.vttp_project.repositories.UserRepository;

@Service 
public class AuthService implements UserDetailsService {

    @Autowired
    private final JwtConfig jwtConfig;
    @Autowired
    private UserRepository userRepo;

  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found!");
        }
        return new UserPrincipal(user);
    }

//    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
//         List<GrantedAuthority> authorities = new ArrayList<>();
//         roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
//         return authorities;
//     }

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

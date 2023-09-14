package sg.nus.edu.iss.vttp_project.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import sg.nus.edu.iss.vttp_project.models.UserPrincipal;

@Service

public class SecurityTokenService {
    @Value("${jwt.key.secret}")
    private String secretKey;
    public String generateToken(@AuthenticationPrincipal UserPrincipal auth) {
        UserPrincipal userPrincipal = (UserPrincipal) ((Authentication) auth).getPrincipal();
        Instant now = Instant.now();
        String scope = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return JWT.create()
                .withIssuer("admin")
                .withIssuedAt(now)
                .withSubject(String.valueOf(userPrincipal.getUser().getId()))
                .withExpiresAt(now.plus(1, ChronoUnit.HOURS))
                .withClaim("name", ((Principal) auth).getName())
                .withClaim("scope", scope)
                .sign(Algorithm.HMAC256(secretKey));
    }
}
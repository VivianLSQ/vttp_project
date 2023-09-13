package sg.nus.edu.iss.vttp_project.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Configuration
@EnableWebSecurity
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public Key jwtSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
            //used for secure message authentication 
        //return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
            //used for data representation or exchange
    }

    @Value("${jwt.expirationInMs}")
    private long expirationInMs;

    public long getExpirationInMs() {
        return expirationInMs;
    }

    public SignatureAlgorithm getAlgorithm() {
        return SignatureAlgorithm.HS256;
    }
}

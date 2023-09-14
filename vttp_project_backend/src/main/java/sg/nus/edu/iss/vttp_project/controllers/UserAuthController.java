package sg.nus.edu.iss.vttp_project.controllers;

import java.util.Date;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;
import sg.nus.edu.iss.vttp_project.models.User;
import sg.nus.edu.iss.vttp_project.models.UserPrincipal;
import sg.nus.edu.iss.vttp_project.services.AuthService;
import sg.nus.edu.iss.vttp_project.services.SecurityTokenService;
import sg.nus.edu.iss.vttp_project.services.UserService;


@Controller
@RequestMapping(path="/api/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Log
public class UserAuthController {

    @Autowired
    AuthService authSvc; 
    SecurityTokenService securityTokenSvc; 
    @Autowired
    UserService userSvc; 

  @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
         //Endpoint for users to create an account and store their information in the database
        Optional<User> userAdded = userSvc.addNewUser(user);
        JsonObjectBuilder msgBuilder = Json.createObjectBuilder();
        if (userAdded.isPresent()) {
            msgBuilder.add("message", "Account successfully registered");
            msgBuilder.add("timestamp", new Date().getTime());
            return ResponseEntity.status(HttpStatus.CREATED).body(msgBuilder.build().toString());
        } else {
            msgBuilder.add("message", "Account registration failed");
            msgBuilder.add("timestamp", new Date().getTime());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgBuilder.build().toString());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<User> login(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        //Endpoint where users can provide their credentials (e.g., username and password) to authenticate 
        //Can use JWT Here to maintain user sessions and track user state
        log.info("Successful login for %s. Generating token...");
        String token = securityTokenSvc.generateToken(userPrincipal);
        if (token != null) {
            log.info("Token generated: %s");
            final User user = userPrincipal.getUser();
            return ResponseEntity.ok(new User(null, token, user.getUsername(), user.getEmail(), user.getRoles(), user.getHomeAddress(), user.getWorkAddress()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
         JsonObjectBuilder msgBuilder = Json.createObjectBuilder();
            msgBuilder.add("message", "User has logged out");
            msgBuilder.add("timestamp", new Date().getTime());
            return ResponseEntity.status(200).body(msgBuilder.build().toString());
    }

    
}

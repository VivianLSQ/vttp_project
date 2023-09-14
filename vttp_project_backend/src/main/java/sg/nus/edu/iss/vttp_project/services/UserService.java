package sg.nus.edu.iss.vttp_project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.vttp_project.repositories.UserRepository;

import sg.nus.edu.iss.vttp_project.models.User;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo; 
    @Autowired
    private PasswordEncoder pwEncoder; 

    public Optional<User> getUserById(Integer userId) {
        return Optional.ofNullable(userRepo.getUserById(userId));
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepo.getUserByEmail(email));
    }

    public Optional<User> addNewUser(User user) {
        user.setPassword(pwEncoder.encode(user.getPassword()));
        Integer userId = userRepo.addNewUser(user);
        if (userId != null) {
            user.setId(userId);
            return Optional.of(user);
        }
        return Optional.empty(); 
    }

    public Boolean updateUser(User user) {
        return userRepo.updateUser(user) > 0;
    }

}

package sg.nus.edu.iss.vttp_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sg.nus.edu.iss.vttp_project.models.User;
import sg.nus.edu.iss.vttp_project.models.UserPrincipal;
import sg.nus.edu.iss.vttp_project.repositories.UserRepository;

public class AuthSvc2 implements UserDetailsService{

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
    
}

package sg.nus.edu.iss.vttp_project.models;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(user.getRoles().split(","))
                .map(role -> new SimpleGrantedAuthority(role))
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user != null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user != null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user != null;
    }

    @Override
    public boolean isEnabled() {
        return user != null;
    }
}
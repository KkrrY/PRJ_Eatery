package authorization;

import entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class UserDetailsServices {
    @Bean
    public UserDetailsService userDetailsService (UserRepository userRepo) {
        return username -> {
            UserEntity user = userRepo.findByUsername(username);
            if (user != null) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                // Assuming user.getAuthorities() returns a list of roles/authorities
                user.getAuthorities().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                });
                //return new UserEntity(user.getUsername(), user.getPassword(), authorities); //this approach is limited

                return new CustomUserDetails(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        authorities,
                        user.getFullname(),
                        user.getStreet(),
                        user.getCity(),
                        user.getCountry(),
                        user.getZip(),
                        user.getPhoneNumber()
                );

            }
            throw new UsernameNotFoundException("UserEntity '" + username + "' not found");
        };
    }
}


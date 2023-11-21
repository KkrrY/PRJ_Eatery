package com.example.prj_eatery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

class CustomUserDetails extends User { //making an additional class that is capable to handle more than 3 main parameters
    //E.g. we add a data we explicitly need
    private Long id;
    private final String fullname;
    private final String street;
    private final String city;
    private final String country;
    private final String zip;
    private final String phoneNumber;

    public CustomUserDetails(
            Long id,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            String fullname,
            String street,
            String city,
            String country,
            String zip,
            String phoneNumber
    ) {
        super(username, password, authorities);
        this.id = id;
        this.fullname = fullname;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullname;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

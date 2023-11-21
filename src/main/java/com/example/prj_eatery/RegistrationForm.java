package com.example.prj_eatery;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String country;
    private String zip;
    private String phone;
    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        return new UserEntity(
                username, passwordEncoder.encode(password),
                fullname, street, city, country, zip, phone);
    }
}
package com.example.prj_eatery;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/*
In the previous chapter, you settled on using Spring Data JPA as your persistence
option for all taco, ingredient, and order data. It would thus make sense to persist
user data in the same way. If you do so, the data will ultimately reside in a relational
database, so you could use JDBC authentication. But it’d be even better to leverage
the Spring Data JPA repository used to store users.
First things first, though. Let’s create the domain object and repository interface
that represents and persists user information.
DEFINING THE USER DOMAIN AND PERSISTENCE
When Taco Cloud customers register with the application, they’ll need to provide more
than just a username and password. They’ll also give you their full name, address, and
phone number. This information can be used for a variety of purposes, including prepopulating
the order form (not to mention potential marketing opportunities).
To capture all of that information, you’ll create a UserEntity class, as follows.
*/
@Entity
@Data
@Table(name = "Users")
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
@RequiredArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String username;
    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    @Column(name = "country")
    private final String country;
    private final String zip;
    private final String phoneNumber;

    @Transient //exclude value from participating in table as column
    private String role = "USER";  // Add a role field
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}


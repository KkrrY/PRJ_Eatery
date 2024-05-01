package entity;

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

@Entity
@Data
@Table(name = "Users")
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
@RequiredArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, unique = true)
    private final String username;
    private final String password;
    @Column(length = 40)
    private final String fullname;
    @Column(length = 30)
    private final String street;
    @Column(length = 20)
    private final String city;
    @Column(name = "country", length = 20)
    private final String country;
    @Column(length = 6)
    private final String zip;
    @Column(length = 20)
    private final String phoneNumber;

    @Transient
    private String role = "ROLE_USER";  // Add a role field
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


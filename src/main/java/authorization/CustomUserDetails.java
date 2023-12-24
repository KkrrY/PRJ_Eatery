package authorization;

import entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User { //making an additional class that is capable to handle more than 3 main parameters
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

    public String getPassword() {
        return super.getPassword();
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

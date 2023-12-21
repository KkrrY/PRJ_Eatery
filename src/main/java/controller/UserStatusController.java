package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user-role")
public class UserStatusController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> getUserRole (Authentication authentication) {
        return authentication == null
                ? new ResponseEntity<>(false, HttpStatus.OK)
                : new ResponseEntity<>(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")), HttpStatus.OK);
    }
}

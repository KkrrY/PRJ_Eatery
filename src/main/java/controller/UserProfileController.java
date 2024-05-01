package controller;

import entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

@RequestMapping("/profile")
@Controller
public class UserProfileController {
    @GetMapping
    public ResponseEntity<UserEntity> showUserData(@SessionAttribute UserEntity user) {
        return new ResponseEntity<>(user, user == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }


}

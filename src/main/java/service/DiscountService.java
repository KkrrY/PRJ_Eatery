package service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DiscountService {

    public static double calculateDiscount() {
        return getCurrentUserRole().contains(new SimpleGrantedAuthority("ROLE_USER")) ? 0.95 : 1.0;
    }

    private static Collection<? extends GrantedAuthority> getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
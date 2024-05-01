package service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import service.DiscountService;

import java.util.Collection;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Override
    public double calculateDiscount() {
        return getCurrentUserRole().contains(new SimpleGrantedAuthority("ROLE_USER")) ? 0.95 : 1.0;
    }

    private static Collection<? extends GrantedAuthority> getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
package service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public static double calculateDiscount() {
        String userRole = getCurrentUserRole();
        return userRole.equals("user") ? 0.95 : 1.0;
    }

    private static String getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
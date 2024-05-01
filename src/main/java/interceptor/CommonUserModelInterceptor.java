package interceptor;

import entity.Orders;
import entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.Principal;

@Component
public class CommonUserModelInterceptor implements HandlerInterceptor { //now add to config
    private final UserDetailsService UserDetailsService;

    @Autowired
    public CommonUserModelInterceptor(UserDetailsService UserDetailsService) {
        this.UserDetailsService = UserDetailsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserEntity user = getUser(request.getUserPrincipal());
        session.setAttribute("order", getOrder(user.getUsername()));
        session.setAttribute("user", user);
        request.setAttribute("user", user);

        return true;
    }

    private Orders getOrder(String username) {
        Orders order = new Orders();
        order.setUserName(username);
        return order;
    }

    private UserEntity getUser(Principal principal) {
        String guestRole = "Guest";
        String username = principal == null ? guestRole : principal.getName();
        UserEntity user;
        if (username.equals(guestRole)) {
            user = new UserEntity("Guest", "Guest", "Guest", "Guest", "Guest", "Guest", "Guest", "Guest");
            user.setRole("GUEST");
        } else {
            user = (UserEntity) UserDetailsService.loadUserByUsername(username);
        }
        return user;
    }
}

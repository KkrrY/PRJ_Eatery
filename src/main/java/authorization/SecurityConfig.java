package authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationProvider authenticationProvider;
    @Autowired
    public SecurityConfig(CustomAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authenticationProvider( authenticationProvider )
                //.headers(headers -> headers.contentTypeOptions(content -> content.disable()))
                //.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable() )
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
//                                        .requestMatchers(
//                                                "/register",
//                                                "/",
//                                                "/design",
//                                                "/dishes",
//                                                "/orders",
//                                                "/orders/current"
//                                        ).permitAll()
//                                        .requestMatchers("/recent-orders/orders").hasRole("USER")
//                                        .anyRequest().hasRole("ADMIN")
                                        .requestMatchers("/sensors").hasAnyRole("USER")
                                        .requestMatchers("/register").permitAll()
                                        .anyRequest().permitAll()

                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .loginProcessingUrl("/authenticate")
                                .failureUrl("/login?error=true")
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );

        return http.build();
    }



}

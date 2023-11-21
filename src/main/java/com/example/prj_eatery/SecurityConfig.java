package com.example.prj_eatery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        //.requestMatchers("/design", "/orders", "/orders/current").hasAnyRole("USER")
                                        .anyRequest().permitAll() //S37 change
//                                .requestMatchers("/design", "/orders").hasRole("USER")
//                                .requestMatchers("/register").permitAll() // Allow access to /register and /login for ALL users who follow this url
//                                .requestMatchers("/", "/**").hasRole("USER")


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

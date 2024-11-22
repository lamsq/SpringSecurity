package com.example.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll().requestMatchers("/admin/**")
                .hasRole("ADMIN").requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()).formLogin(Customizer.withDefaults()).logout(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails a = User.withDefaultPasswordEncoder().username("admin").password("123").roles("ADMIN").build();
        UserDetails u = User.withDefaultPasswordEncoder().username("user").password("123").roles("USER").build();
        return new InMemoryUserDetailsManager(a, u);
    }
}

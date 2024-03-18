package com.gau.security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("anhtuan")).roles("admin").build();
        UserDetails user = User.withUsername("user").password(passwordEncoder.encode("pwd1")).roles("user").build();
        return new InMemoryUserDetailsManager(admin,user);
    }
@Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home").permitAll()
                .and().authorizeHttpRequests()
                .requestMatchers("hello").permitAll()
                .and().authorizeHttpRequests()
                .requestMatchers("/customer/**").hasRole("admin")
                .and()
                .formLogin()
                .defaultSuccessUrl("/home")
                .and()
                .build();
    }


}

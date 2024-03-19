package com.gau.security.Config;

import com.gau.security.Repository.CustomerRepository;
import com.gau.security.Service.CustomerSVDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomerRepository customerRepository;
    public SecurityConfig(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
@Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("anhtuan")).roles("admin").build();
//        UserDetails user = User.withUsername("user").password(passwordEncoder.encode("pwd1")).roles("user").build();
//        UserDetails user2 = User.withUsername("user2").password(passwordEncoder.encode("pwd2")).roles("seller").build();
//        return new InMemoryUserDetailsManager(admin,user,user2);
    return new CustomerSVDetailService(customerRepository);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a.requestMatchers("/","/user/new","/customer").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/home").authenticated())
                .authorizeHttpRequests(a->a.requestMatchers("/customer/**").hasRole("admin"))
                .authenticationProvider(authenticationProvider())
                .formLogin(a->a.failureUrl("/login?accessDenied").defaultSuccessUrl("/home"))
                .build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}

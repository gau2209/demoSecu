package com.gau.security.Service;

import com.gau.security.Entity.Customer;
import com.gau.security.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerSVDetailService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<Customer> customer = customerRepository.findByUsername(username);
         return customer.map(CustomerUserDetail::new)
                 .orElseThrow(()->new UsernameNotFoundException("User not found" + username));
    }
}

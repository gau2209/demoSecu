package com.gau.security.Service.Impl;


import com.gau.security.Entity.Customer;
import com.gau.security.Repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        public String addUser(Customer customer){
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(customer);
            return "add success";
        }

}

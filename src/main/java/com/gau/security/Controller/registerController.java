package com.gau.security.Controller;

import com.gau.security.Entity.Customer;
import com.gau.security.Repository.CustomerRepository;
import com.gau.security.Service.Impl.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class registerController {
    private final CustomerService customerService;

    public registerController(CustomerService customerService) {
        this.customerService = customerService;
    }

@PostMapping("/new")
    public String addUser(@RequestBody Customer customer){
        return this.customerService.addUser(customer);
    }
}

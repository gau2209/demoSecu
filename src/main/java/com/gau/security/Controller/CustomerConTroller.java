package com.gau.security.Controller;

import com.gau.security.Entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
@Controller

public class CustomerConTroller {
    final private List<Customer> customers = List.of(
            Customer.builder().idUser(1).username("customer1").role("user").mail("customer1@gmail.com").build(),
            Customer.builder().idUser(2).username("customer2").mail("customer2@gmail.com").role("admin").build()
    );
    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Đây là trang index");
}
    @GetMapping("/home")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("Đây là trang home để test Security hehe");
}

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customers = this.customers;
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id){
        List<Customer> customers = this.customers.stream().filter(customer -> customer.getIdUser().equals(id)).toList();
        return ResponseEntity.ok(customers.get(0));
    }



}

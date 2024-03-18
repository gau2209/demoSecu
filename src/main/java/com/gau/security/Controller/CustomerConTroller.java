package com.gau.security.Controller;

import com.gau.security.Entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CustomerConTroller {
    final private List<Customer> customers = List.of(
            Customer.builder().id("001").phone("0111").userName("customer1").build(),
            Customer.builder().id("002").phone("0222").userName("customer2").build()
    );
    @GetMapping("/hello")
public ResponseEntity<String> hello(){
    return ResponseEntity.ok("Hello dc chap nhan");
}
@GetMapping
public ResponseEntity<String> index(){
        return ResponseEntity.ok("Đây là trang home để test Security hehe");
}

@GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id){
        List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
        return ResponseEntity.ok(customers.get(0));
    }

}

package com.mymobile.controller;

import com.mymobile.entity.Customer;
import com.mymobile.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer savedcustomer= customerService.saveCustomer(customer);
        return new ResponseEntity(savedcustomer,HttpStatus.OK);
    }
    @GetMapping("/{customerId}")
    public  ResponseEntity<Customer> getCustomer(@PathVariable String customerId){
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

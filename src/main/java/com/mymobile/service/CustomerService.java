package com.mymobile.service;

import com.mymobile.entity.Customer;
import com.mymobile.repo.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer getCustomerById(String customerId){
        return customerRepository.findById(customerId).orElse(null);
    }

}

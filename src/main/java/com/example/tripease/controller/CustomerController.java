package com.example.tripease.controller;


import com.example.tripease.model.Customer;
import com.example.tripease.repository.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        return customerService.addCustomer(customer);
    }
    @GetMapping("/get/customer-id/{id}")
    public Customer getCustomer(@PathVariable("id") Integer customerId)
    {
       return  customerService.getCustomer(customerId);
    }


}

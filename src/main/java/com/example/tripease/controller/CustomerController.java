package com.example.tripease.controller;


import com.example.tripease.Enum.Gender;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest)
    {
        return customerService.addCustomer(customerRequest);
    }
    @GetMapping("/get/customer-id/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") Integer customerId)
    {
       return  customerService.getCustomer(customerId);
    }
    @GetMapping("/get/gender/{gender}")
    public List<CustomerResponse>  getByGender(@PathVariable("gender") Gender gender)
    {
        return customerService.getByGender(gender);
    }
    @GetMapping("get")
    public List<CustomerResponse> getByGenderAndAge(@RequestParam("gender") Gender gender,@RequestParam("age") int age)
    {
        return customerService.getByGenderAndAge(gender,age);
    }
    @GetMapping("/get-by-age-greater-than")
    public List<CustomerResponse> getByGenderAndAgeGreaterThan(@RequestParam("gender") Gender gender,@RequestParam("age") int age)
    {
        return  customerService.getByGenderAndAgeGreaterThan(gender,age);
    }

}

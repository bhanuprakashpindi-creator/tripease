package com.example.tripease.service;

import com.example.tripease.Enum.Gender;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.exception.CustomerNotFoundException;
import com.example.tripease.model.Customer;
import com.example.tripease.repository.CustomerRepository;
import com.example.tripease.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        //request dto -> entity
        Customer customer= CustomerTransformer.customerRequestToCustomer(customerRequest);
        //saved Entity to DB
        Customer savedCustomer=customerRepository.save(customer);
        // Saved Enityt to DTO
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse getCustomer(Integer customerId) {
        Optional<Customer> optionalCustomer= customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty())
        {
            throw new CustomerNotFoundException("Customer Not Present in the database");
        }
        Customer savedCustomer= optionalCustomer.get();
        //Saved entity -> response dto
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }
    public List<CustomerResponse> getByGender(Gender gender)
    {
        List<Customer> customers=customerRepository.findByGender(gender);
        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer : customers)
        {
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }
    public List<CustomerResponse> getByGenderAndAge(Gender gender,int age)
    {
        List<Customer> customers=customerRepository.findByGenderAndAge(gender,age);
        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer : customers)
        {
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getByGenderAndAgeGreaterThan(Gender gender, int age) {
        List<Customer> customers=customerRepository.getByGenderAndAgeGreaterThan(gender,age);
        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer : customers)
        {
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }
}

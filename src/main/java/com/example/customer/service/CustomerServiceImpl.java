package com.example.customer.service;

import com.example.customer.dto.CustomerDto;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto getCustomerById(int id) {
        return customerRepository.findCustomerById(id).map(CustomerDto::new).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email).map(CustomerDto::new).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public void createCustomer(CustomerDto customerDto) {
        customerRepository.save(mapToCustomer(customerDto));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerDto::new).collect(Collectors.toList());
    }

    private Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setUsername(customerDto.getUsername());
        customer.setLastName(customerDto.getLastName());
        customer.setFirstName(customerDto.getFirstName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }
}

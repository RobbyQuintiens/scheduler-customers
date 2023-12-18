package com.example.customer.service;

import com.example.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    public CustomerDto getCustomerById(int id);

    public CustomerDto getCustomerByEmail(String email);

    public void createCustomer(CustomerDto customerDto);

    public List<CustomerDto> getAllCustomers();
}

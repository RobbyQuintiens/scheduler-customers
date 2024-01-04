package com.example.customer.service;

import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.CustomerResource;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;

public interface CustomerService {

    public CustomerDto getCustomerById(int id, String provider);

    public CustomerDto getCustomerByEmail(String email, String provider);

    public void createCustomer(CustomerResource customerResource, String providerId);

    public Page<CustomerDto> getAllCustomers(Predicate predicate, String provider, int page, int size, String sortBy);
}

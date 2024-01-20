package com.example.customer.service;

import com.example.customer.dto.AddressResource;
import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.CustomerResource;
import com.example.customer.model.Address;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;

public interface CustomerService {

    CustomerDto getCustomerById(int id, String provider);

    CustomerDto getCustomerByEmail(String email, String provider);

    void createCustomer(CustomerResource customerResource, String providerId);

    Page<CustomerDto> getAllCustomers(Predicate predicate, String provider, int page, int size, String sortBy);

    void addCustomerAddress(Integer customerId, Address address);
}

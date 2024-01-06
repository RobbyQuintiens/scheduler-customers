package com.example.customer.service;

import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.CustomerResource;
import com.example.customer.model.Customer;
import com.example.customer.model.QCustomer;
import com.example.customer.repository.CustomerRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import exception.CustomerNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerPageRepository customerPageRepository;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerPageRepository customerPageRepository, CustomerRepository customerRepository) {
        this.customerPageRepository = customerPageRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto getCustomerById(int id, String provider) {
        return customerRepository.findCustomerByIdAndProviderId(id, provider).map(CustomerDto::new).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerDto getCustomerByEmail(String email, String provider) {
        return customerRepository.findCustomerByEmailAndProviderId(email, provider).map(CustomerDto::new).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public void createCustomer(CustomerResource customerResource, String providerId) {
        customerRepository.save(mapToCustomer(customerResource, providerId));
    }

    @Override
    public Page<CustomerDto> getAllCustomers(Predicate predicate, String provider, int page, int size, String sortBy) {
        Pageable paging = sortBy(page, size, sortBy);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QCustomer.customer.providerId.eq(provider));

        List<CustomerDto> customers = customerPageRepository.findAll(builder.and(predicate), paging).stream()
                .map(CustomerDto::new)
                .collect(Collectors.toList());
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        return new PageImpl<>(customers, paging, findTotalOfElements(predicate, builder));
    }

    private Customer mapToCustomer(CustomerResource customerResource, String providerId) {
        Customer customer = new Customer();
        customer.setUsername(customerResource.getUsername());
        customer.setLastName(customerResource.getLastName());
        customer.setFirstName(customerResource.getFirstName());
        customer.setEmail(customerResource.getEmail());
        customer.setProviderId(providerId);
        return customer;
    }

    private Pageable sortBy(int page, int size, String sortBy) {
        return PageRequest.of(page, size, Sort.by(sortBy));
    }

    private Long findTotalOfElements(Predicate predicate, BooleanBuilder builder) {
        return customerPageRepository.count(builder.and(predicate));
    }
}

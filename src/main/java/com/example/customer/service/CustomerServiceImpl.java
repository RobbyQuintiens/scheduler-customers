package com.example.customer.service;

import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.CustomerResource;
import com.example.customer.mapper.AddressMapper;
import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.model.QCustomer;
import com.example.customer.repository.AddressRepository;
import com.example.customer.repository.CustomerRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import exception.CustomerNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerPageRepository customerPageRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerServiceImpl(CustomerPageRepository customerPageRepository, CustomerRepository customerRepository,
                               AddressRepository addressRepository) {
        this.customerPageRepository = customerPageRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public CustomerDto getCustomerById(int id, String provider) {
        return customerRepository.findCustomerByIdAndProviderId(id, provider).map(CustomerDto::new).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerDto getCustomerByEmail(String email, String provider) {
        return customerRepository.findCustomerByEmailAndProviderId(email, provider).map(CustomerDto::new).orElseThrow(CustomerNotFoundException::new);
    }

    @Transactional
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
                .map(customer -> new CustomerDto(customer))
                .collect(Collectors.toList());
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        return new PageImpl<>(customers, paging, findTotalOfElements(predicate, builder));
    }

    @Override
    public void addCustomerAddress(Integer customerId, Address address) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        customer.getAddresses().add(address);
        customerRepository.save(customer);
    }

    private Customer mapToCustomer(CustomerResource customerResource, String providerId) {
        Customer customer = new Customer();
        customer.setUsername(customerResource.getUsername());
        customer.setLastName(customerResource.getLastName());
        customer.setFirstName(customerResource.getFirstName());
        customer.setEmail(customerResource.getEmail());
        customer.setProviderId(providerId);
        customer.setCompany(customerResource.isCompany());
        customer.setPhoneNumber(customerResource.getPhoneNumber());
        if (customer.getAddresses() == null) {
            customer.setAddresses(new ArrayList<>());
        }
        customer.getAddresses().add(saveCustomerAddress(customerResource));
        return customer;
    }

    private Address saveCustomerAddress(CustomerResource customerResource) {
        return addressRepository.save(AddressMapper.mapToAddress(customerResource.getAddressResource()));
    }

    private Pageable sortBy(int page, int size, String sortBy) {
        return PageRequest.of(page, size, Sort.by(sortBy));
    }

    private Long findTotalOfElements(Predicate predicate, BooleanBuilder builder) {
        return customerPageRepository.count(builder.and(predicate));
    }
}

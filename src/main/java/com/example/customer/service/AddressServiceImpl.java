package com.example.customer.service;

import com.example.customer.dto.AddressDto;
import com.example.customer.dto.AddressResource;
import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.CustomerResource;
import com.example.customer.mapper.AddressMapper;
import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerService customerService;

    public AddressServiceImpl(AddressRepository addressRepository, CustomerService customerService) {
        this.addressRepository = addressRepository;
        this.customerService = customerService;
    }

    @Override
    public List<AddressDto> getAddressById(Integer customerId, String providerId) {
        List<Integer> addressIds = customerService.getCustomerById(customerId, providerId).getAddresses()
                .stream()
                .map(AddressDto::getId)
                .toList();
        return addressRepository.findAllByIdIn(addressIds)
                .stream()
                .filter(c -> isCustomerFoundForProvider(customerId, providerId))
                .map(AddressDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addAddressToCustomer(AddressResource addressResource, Integer customerId, String providerId) {
        CustomerDto customer = customerService.getCustomerById(customerId, providerId);
        if (customer != null) {
            Address address = addressRepository.save(AddressMapper.mapToAddress(addressResource));
            customerService.addCustomerAddress(customerId, address);
        }
    }

    private boolean isCustomerFoundForProvider(Integer customerId, String providerId) {
        return customerService.getCustomerById(customerId, providerId) != null;
    }
}

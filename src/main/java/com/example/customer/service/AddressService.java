package com.example.customer.service;

import com.example.customer.dto.AddressDto;
import com.example.customer.dto.AddressResource;
import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.CustomerResource;
import com.example.customer.model.Address;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAddressById(Integer customerId, String providerId);

    void addAddressToCustomer(AddressResource addressResource, Integer customerId, String providerId);
}

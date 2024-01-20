package com.example.customer.mapper;

import com.example.customer.dto.AddressResource;
import com.example.customer.model.Address;

public class AddressMapper {

    public AddressMapper() {
    }

    public static Address mapToAddress(AddressResource addressResource) {
        Address address = new Address();
        address.setZipCode(addressResource.getZipcode());
        address.setNumber(addressResource.getNumber());
        address.setStreet(addressResource.getStreet());
        address.setCountry(addressResource.getCountry());
        address.setCity(addressResource.getCity());
        return address;
    }
}

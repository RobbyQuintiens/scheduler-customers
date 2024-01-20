package com.example.customer.dto;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerDto {

    private final int id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String providerId;
    private final boolean company;
    private final String phoneNumber;
    private final List<AddressDto> addresses;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.username = customer.getUsername();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.providerId = customer.getProviderId();
        this.company = customer.isCompany();
        this.phoneNumber = customer.getPhoneNumber();
        this.addresses = setAddresses(customer.getAddresses());
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProviderId() {
        return providerId;
    }

    public boolean isCompany() {
        return company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<AddressDto> setAddresses(List<Address> addresses) {
        return addresses.stream()
                .map(AddressDto::new)
                .collect(Collectors.toList());
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }
}

package com.example.customer.dto;

import jakarta.validation.constraints.NotNull;

public class CustomerResource {

    @NotNull
    private final String username;
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final String email;
    @NotNull
    private final String providerId;
    @NotNull
    private final boolean company;
    @NotNull
    private final String phoneNumber;
    @NotNull
    private final AddressResource addressResource;

    public CustomerResource(String username, String firstName, String lastName, String email, String providerId,
                            boolean company, String phoneNumber, AddressResource addressResource) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.providerId = providerId;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.addressResource = addressResource;
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

    public AddressResource getAddressResource() {
        return addressResource;
    }
}

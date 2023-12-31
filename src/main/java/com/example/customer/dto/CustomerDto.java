package com.example.customer.dto;

import com.example.customer.model.Customer;

public class CustomerDto {

    private final int id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String providerId;
    private final boolean company;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.username = customer.getUsername();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.providerId = customer.getProviderId();
        this.company = customer.isCompany();
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
}

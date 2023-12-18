package com.example.customer.dto;

import com.example.customer.model.Customer;

public class CustomerDto {

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;

    public CustomerDto(Customer customer) {
        this.username = customer.getUsername();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
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
}

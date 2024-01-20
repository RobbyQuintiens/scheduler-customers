package com.example.customer.dto;

import jakarta.validation.constraints.NotNull;

public class AddressResource {

    @NotNull
    private final String street;
    @NotNull
    private final String number;
    @NotNull
    private final String zipcode;
    @NotNull
    private final String city;
    @NotNull
    private final String country;

    public AddressResource(String street, String number, String zipcode, String city, String country) {
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}

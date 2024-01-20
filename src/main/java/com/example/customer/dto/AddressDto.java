package com.example.customer.dto;

import com.example.customer.model.Address;

public class AddressDto {

    private final int id;
    private final String street;
    private final String number;
    private final String zipcode;
    private final String city;
    private final String country;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.zipcode = address.getZipCode();
        this.city = address.getCity();
        this.country = address.getCountry();
    }

    public int getId() {
        return id;
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

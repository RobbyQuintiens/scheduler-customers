package com.example.customer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUSTOMER")
    private Integer id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ID_PROVIDER")
    private String providerId;
    @Column(name = "IS_COMPANY")
    private boolean company;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_CUSTOMER")
    private List<Address> addresses;
}

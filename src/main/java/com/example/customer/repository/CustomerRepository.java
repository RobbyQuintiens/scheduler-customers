package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findCustomerById(int id);

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerByUsername(String username);

    List<Customer> findCustomersByLastName(String lastName);

}

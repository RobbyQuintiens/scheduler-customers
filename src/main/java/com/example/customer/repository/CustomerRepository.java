package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByProviderId(String provider);

    Optional<Customer> findCustomerByIdAndProviderId(int id, String provider);

    Optional<Customer> findCustomerByEmailAndProviderId(String email, String provider);

    Optional<Customer> findCustomerByUsernameAndProviderId(String username, String provider);

    List<Customer> findCustomersByLastNameAndProviderId(String lastName, String provider);

    List<Customer> findCustomersByCompanyAndProviderId(boolean company, String provider);

}

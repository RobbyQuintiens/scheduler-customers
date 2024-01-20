package com.example.customer.repository;

import com.example.customer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAllByIdIn(List<Integer> ids);
}

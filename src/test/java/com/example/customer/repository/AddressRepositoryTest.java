package com.example.customer.repository;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    private Address address;

    @Test
    public void findAddressByIdTest() {
        createAddress();
        Optional<Address> foundAddress = addressRepository.findById(address.getId());

        assertThat(foundAddress.get().getId()).isEqualTo(address.getId());
        assertThat(foundAddress.get().getCity()).isEqualTo(address.getCity());
    }

    private void createAddress() {
        address = new Address();
        address.setId(1);
        address.setCity("City");
        address.setCountry("Belgium");
        address.setStreet("Street");
        address.setNumber("10b");
        address.setZipCode("3500");
        addressRepository.save(address);
    }
}

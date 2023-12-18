package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @Test
    public void findCustomerByIdTest() {
        createCustomer();
        Optional<Customer> foundCustomer = customerRepository.findCustomerById(customer.getId());

        assertThat(foundCustomer.get().getId()).isEqualTo(customer.getId());
        assertThat(foundCustomer.get().getEmail()).isEqualTo(customer.getEmail());
        assertThat(foundCustomer.get().getUsername()).isEqualTo(customer.getUsername());
        assertThat(foundCustomer.get().getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(foundCustomer.get().getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    public void findCustomerByEmailTest() {
        createCustomer();
        Optional<Customer> foundCustomer = customerRepository.findCustomerByEmail(customer.getEmail());

        assertThat(foundCustomer.get().getId()).isEqualTo(customer.getId());
        assertThat(foundCustomer.get().getEmail()).isEqualTo(customer.getEmail());
        assertThat(foundCustomer.get().getUsername()).isEqualTo(customer.getUsername());
        assertThat(foundCustomer.get().getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(foundCustomer.get().getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    public void findCustomerByUsernameTest() {
        createCustomer();
        Optional<Customer> foundCustomer = customerRepository.findCustomerByUsername(customer.getUsername());

        assertThat(foundCustomer.get().getId()).isEqualTo(customer.getId());
        assertThat(foundCustomer.get().getEmail()).isEqualTo(customer.getEmail());
        assertThat(foundCustomer.get().getUsername()).isEqualTo(customer.getUsername());
        assertThat(foundCustomer.get().getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(foundCustomer.get().getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    public void findCustomersByLastNameTest() {
        createCustomer();
        List<Customer> foundCustomers = customerRepository.findCustomersByLastName(customer.getLastName());

        assertThat(foundCustomers.size()).isEqualTo(2);
    }

    private void createCustomer() {
        customer = new Customer();
        customer.setId(1);
        customer.setEmail("email@test.be");
        customer.setUsername("username");
        customer.setFirstName("first name");
        customer.setLastName("last name");
        customerRepository.save(customer);
        customerRepository.save(createSecondCustomer());
    }

    private Customer createSecondCustomer() {
        Customer customer = new Customer();
        customer.setEmail("second@test.be");
        customer.setUsername("second");
        customer.setFirstName("first");
        customer.setLastName("last name");
        return customer;
    }
}

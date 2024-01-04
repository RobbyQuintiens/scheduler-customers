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
        Optional<Customer> foundCustomer = customerRepository.findCustomerByIdAndProviderId(customer.getId(), customer.getProviderId());

        assertThat(foundCustomer.get().getId()).isEqualTo(customer.getId());
        assertThat(foundCustomer.get().getEmail()).isEqualTo(customer.getEmail());
        assertThat(foundCustomer.get().getUsername()).isEqualTo(customer.getUsername());
        assertThat(foundCustomer.get().getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(foundCustomer.get().getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    public void findCustomerByEmailTest() {
        createCustomer();
        Optional<Customer> foundCustomer = customerRepository.findCustomerByEmailAndProviderId(customer.getEmail(), customer.getProviderId());

        assertThat(foundCustomer.get().getId()).isEqualTo(customer.getId());
        assertThat(foundCustomer.get().getEmail()).isEqualTo(customer.getEmail());
        assertThat(foundCustomer.get().getUsername()).isEqualTo(customer.getUsername());
        assertThat(foundCustomer.get().getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(foundCustomer.get().getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    public void findCustomerByUsernameTest() {
        createCustomer();
        Optional<Customer> foundCustomer = customerRepository.findCustomerByUsernameAndProviderId(customer.getUsername(), customer.getProviderId());

        assertThat(foundCustomer.get().getId()).isEqualTo(customer.getId());
        assertThat(foundCustomer.get().getEmail()).isEqualTo(customer.getEmail());
        assertThat(foundCustomer.get().getUsername()).isEqualTo(customer.getUsername());
        assertThat(foundCustomer.get().getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(foundCustomer.get().getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    public void findCustomersByLastNameTest() {
        createCustomer();
        List<Customer> foundCustomers = customerRepository.findCustomersByLastNameAndProviderId(customer.getLastName(), customer.getProviderId());

        assertThat(foundCustomers.size()).isEqualTo(2);
    }

    @Test
    public void findAllCustomers() {
        createCustomer();
        List<Customer> foundCustomers = customerRepository.findAllByProviderId(customer.getProviderId());

        assertThat(foundCustomers.size()).isEqualTo(2);
    }

    @Test
    public void findCustomersByCompanyIsTrue() {
        createCustomer();
        List<Customer> foundCustomers = customerRepository.findCustomersByCompanyAndProviderId(true, customer.getProviderId());

        assertThat(foundCustomers.size()).isEqualTo(1);
        assertThat(foundCustomers.get(0).getUsername()).isEqualTo("username");
    }

    @Test
    public void findCustomersByCompanyIsFalse() {
        createCustomer();
        List<Customer> foundCustomers = customerRepository.findCustomersByCompanyAndProviderId(false, customer.getProviderId());

        assertThat(foundCustomers.size()).isEqualTo(1);
        assertThat(foundCustomers.get(0).getUsername()).isEqualTo("second");
    }

    private void createCustomer() {
        customer = new Customer();
        customer.setId(1);
        customer.setEmail("email@test.be");
        customer.setUsername("username");
        customer.setFirstName("first name");
        customer.setLastName("last name");
        customer.setProviderId("100");
        customer.setCompany(true);
        customerRepository.save(customer);
        customerRepository.save(createSecondCustomer());
    }

    private Customer createSecondCustomer() {
        Customer customer = new Customer();
        customer.setEmail("second@test.be");
        customer.setUsername("second");
        customer.setFirstName("first");
        customer.setLastName("last name");
        customer.setProviderId("100");
        customer.setCompany(false);
        return customer;
    }
}

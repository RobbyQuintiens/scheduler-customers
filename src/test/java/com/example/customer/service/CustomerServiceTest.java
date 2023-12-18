package com.example.customer.service;

import com.example.customer.dto.CustomerDto;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void getCustomerByIdTest() {
        Customer customer = createCustomer(1, "test@email.be", "user", "first", "last");
        when(customerRepository.findCustomerById(1)).thenReturn(Optional.of(customer));

        CustomerDto foundCustomer = customerService.getCustomerById(1);

        assertThat(foundCustomer.getUsername()).isEqualTo(customer.getUsername());
    }

    @Test
    public void getCustomerByEmailTest() {
        Customer customer = createCustomer(1, "test@email.be", "user", "first", "last");
        when(customerRepository.findCustomerByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

        CustomerDto foundCustomer = customerService.getCustomerByEmail(customer.getEmail());

        assertThat(foundCustomer.getUsername()).isEqualTo(customer.getUsername());
    }

    @Test
    public void createCustomerTest() {
        Customer customer = createCustomer(1, "test@email.be", "user", "first", "last");
        CustomerDto customerDto = new CustomerDto(customer);
        customerService.createCustomer(customerDto);
    }

    @Test
    public void getAllCustomers() {
        Customer customerOne = createCustomer(1, "test@email.be", "user", "first", "last");
        Customer customerTwo = createCustomer(2, "test2@email.be", "user2", "first", "last2");
        when(customerRepository.findAll()).thenReturn(List.of(customerOne, customerTwo));

        List<CustomerDto> foundCustomers = customerService.getAllCustomers();

        assertThat(foundCustomers.size()).isEqualTo(2);
        assertThat(foundCustomers.get(0).getUsername()).isEqualTo(customerOne.getUsername());
    }

    private Customer createCustomer(int id, String email, String username, String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setEmail(email);
        customer.setUsername(username);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customer;
    }
}

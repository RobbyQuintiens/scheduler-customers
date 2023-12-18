package com.example.customer.controller;

import com.example.customer.dto.CustomerDto;
import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    private List<Customer> customers;
    private Customer customer;
    private Customer customer2;

    public void init() {
        customer = createCustomer(1, "test@email.be", "user", "first", "last");
        customer2 = createCustomer(2, "test2@email.be", "user2", "first", "last2");
        customers = List.of(customer, customer2);
    }

    @Test
    public void getAllCustomersTest() {
        init();
        List<CustomerDto> customerList = customers.stream().map(CustomerDto::new).toList();
        when(customerService.getAllCustomers()).thenReturn(customerList);

        ResponseEntity<List<CustomerDto>> responseEntity = customerController.getAllCustomers();

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void getCustomerByIdTest() {
        init();
        CustomerDto customerDto = Optional.of(customer).map(CustomerDto::new).get();
        when(customerService.getCustomerById(customer.getId())).thenReturn(customerDto);

        ResponseEntity<CustomerDto> responseEntity = customerController.getCustomerById(customer.getId());

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getUsername()).isEqualTo(customer.getUsername());
    }

    @Test
    public void getCustomerByEmailTest() {
        init();
        CustomerDto customerDto = Optional.of(customer).map(CustomerDto::new).get();
        when(customerService.getCustomerByEmail(customer.getEmail())).thenReturn(customerDto);

        ResponseEntity<CustomerDto> responseEntity = customerController.getCustomerByEmail(customer.getEmail());

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getFirstName()).isEqualTo(customer.getFirstName());

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

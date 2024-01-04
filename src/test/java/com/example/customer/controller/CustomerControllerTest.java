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
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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

    HttpHeaders token;

    private List<Customer> customers;
    private Customer customer;
    private Customer customer2;

    public void init() {
        customer = createCustomer(1, "test@email.be", "user", "first", "last");
        customer2 = createCustomer(2, "test2@email.be", "user2", "first", "last2");
        customers = List.of(customer, customer2);
        token = new HttpHeaders();
        token.set("sub", customer.getProviderId());
    }

//    @Test
//    public void getAllCustomersTest() {
//        init();
//        List<CustomerDto> customerList = customers.stream().map(CustomerDto::new).toList();
//        when(customerService.getAllCustomers(customer.getProviderId())).thenReturn(customerList);
//
//        ResponseEntity<List<CustomerDto>> responseEntity = customerController.getAllCustomers(token);
//
//        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
//    }

//    @Test
//    public void getCustomerByIdTest() {
//        init();
//        CustomerDto customerDto = Optional.of(customer).map(CustomerDto::new).get();
//        when(customerService.getCustomerById(customer.getId(), customer.getProviderId())).thenReturn(customerDto);
//
//        ResponseEntity<CustomerDto> responseEntity = customerController.getCustomerById(customer.getId(), token);
//
//        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
//    }
//
//    @Test
//    public void getCustomerByEmailTest() {
//        init();
//        CustomerDto customerDto = Optional.of(customer).map(CustomerDto::new).get();
//        when(customerService.getCustomerByEmail(customer.getEmail(), customer.getProviderId())).thenReturn(customerDto);
//
//        ResponseEntity<CustomerDto> responseEntity = customerController.getCustomerByEmail(customer.getEmail(), token);
//
//        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
//    }

    private Customer createCustomer(int id, String email, String username, String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setEmail(email);
        customer.setUsername(username);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setProviderId("100");
        return customer;
    }
}

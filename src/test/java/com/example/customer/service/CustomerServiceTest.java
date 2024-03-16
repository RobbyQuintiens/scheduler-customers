package com.example.customer.service;

import com.example.customer.dto.CustomerDto;
import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerPageRepository;
import com.example.customer.repository.CustomerRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerPageRepository customerPageRepository;

    private Predicate predicate;
    private BooleanBuilder builder;
    private Customer customerJaak;
    private Customer customerMarc;
    private Customer customerJos;
    private Address addressJaak;
    private Address addressMarc;
    private Address addressJos;

    public void init() {
        builder = new BooleanBuilder();
        customerJaak = createCustomer(1, "jaak@email.be", "Jaakske", "Jaak", "Aerts", false);
        customerMarc = createCustomer(2, "marc@email.be", "Marcske", "Marc", "Vandenbroek", false);
        customerJos = createCustomer(3, "jos@email.be", "Joske", "Jos", "Uitdenbroek", true);
        addressJaak = createAddress(1, "Straat", "20", "Hasselt", "3500", "Belgium");
        addressMarc = createAddress(2, "Straat", "21", "Hasselt", "3500", "Belgium");
        addressJos = createAddress(3, "Straat", "22", "Hasselt", "3500", "Belgium");

        customerJaak.setAddresses(singletonList(addressJaak));
        customerMarc.setAddresses(singletonList(addressMarc));
        customerJos.setAddresses(singletonList(addressJos));
    }

    @Test
    public void getCustomerByIdTest() {
        init();
        when(customerRepository.findCustomerByIdAndProviderId(customerJaak.getId(), customerJaak.getProviderId())).thenReturn(Optional.of(customerJaak));

        CustomerDto foundCustomer = customerService.getCustomerById(customerJaak.getId(), customerJaak.getProviderId());

        assertThat(foundCustomer.getUsername()).isEqualTo(customerJaak.getUsername());
    }

    @Test
    public void getCustomerByEmailTest() {
        init();
        when(customerRepository.findCustomerByEmailAndProviderId(customerJaak.getEmail(), customerJaak.getProviderId())).thenReturn(Optional.of(customerJaak));

        CustomerDto foundCustomer = customerService.getCustomerByEmail(customerJaak.getEmail(), customerJaak.getProviderId());

        assertThat(foundCustomer.getUsername()).isEqualTo(customerJaak.getUsername());
    }

    @Test
    public void getAllCustomersByProvider() {
        init();
        List<Customer> customers = new LinkedList<>();
        customers.add(customerJaak);
        customers.add(customerJos);
        customers.add(customerMarc);
        Page<Customer> customerPage = new PageImpl<>(customers);
        Pageable paging = PageRequest.of(0, 5);

        when(customerPageRepository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(customerPage);
        Page<CustomerDto> foundCustomersPage = customerService.getAllCustomers(predicate, "100", paging.getPageNumber(), paging.getPageSize(), "username");
        List<CustomerDto> foundCustomersList = foundCustomersPage.get().toList();

        assertThat(foundCustomersList.size()).isEqualTo(3);
        assertThat(foundCustomersList.get(0).getUsername()).isEqualTo(customerJaak.getUsername());
    }

//    @Test
//    public void addCustomerAddress() {
//        init();
//        Address secondAddressJaak = createAddress(4, "Straat", "24", "Genk", "3600", "Belgium");
//
//        when(customerRepository.findById(customerJaak.getId())).thenReturn(Optional.ofNullable(customerJaak));
//
//        customerService.addCustomerAddress(customerJaak.getId(), secondAddressJaak);
//
//        verify(customerRepository).save(customerJaak);
//        assertThat(customerJaak.getAddresses().size()).isEqualTo(2);
//    }

    private Customer createCustomer(int id, String email, String username, String firstName, String lastName, boolean company) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setEmail(email);
        customer.setUsername(username);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setProviderId("100");
        customer.setCompany(company);
        return customer;
    }

    private Address createAddress(Integer id, String street, String number, String city, String zipCode, String country) {
        Address address = new Address();
        address.setId(id);
        address.setCountry(country);
        address.setStreet(street);
        address.setCity(city);
        address.setNumber(number);
        address.setZipCode(zipCode);
        return address;
    }
}

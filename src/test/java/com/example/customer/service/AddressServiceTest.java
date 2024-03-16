package com.example.customer.service;

import com.example.customer.dto.AddressDto;
import com.example.customer.dto.AddressResource;
import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.CustomerResource;
import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.repository.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CustomerService customerService;

    private Address address;
    private Address address2;
    private Customer customer;

    public void init() {
        customer = createCustomer(1, "jaak@email.be", "Jaakske", "Jaak", "Aerts", false);
        address = createAddress(1, "Straat", "20", "Hasselt", "3500", "Belgium");
        address2 = createAddress(2, "Straat", "21", "Hasselt", "3500", "Belgium");
        customer.setAddresses(List.of(address, address2));
    }

    @Test
    public void getAddressById() {
        init();

        when(customerService.getCustomerById(customer.getId(), customer.getProviderId())).thenReturn(new CustomerDto(customer));
        when(addressRepository.findAllByIdIn(List.of(address.getId(), address2.getId()))).thenReturn(List.of(address, address2));

        List<AddressDto> foundAddresses = addressService.getAddressById(customer.getId(), customer.getProviderId());

        assertThat(foundAddresses.size()).isEqualTo(2);
    }

    // USE Captor
//    @Test
//    public void addAddressToCustomer() {
//        init();
//        Address address3 = createAddress(null, "Straat", "25", "Hasselt", "3500", "Belgium");
//
//        when(customerService.getCustomerById(customer.getId(), customer.getProviderId())).thenReturn(new CustomerDto(customer));
//
//        addressService.addAddressToCustomer(createAddressResource(address3), customer.getId(), customer.getProviderId());
//
//        verify(addressRepository).save(address3);
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

    private CustomerResource createCustomerResource(Customer customer, AddressResource addressResource) {
        return new CustomerResource(customer.getUsername(), customer.getFirstName(),
                customer.getLastName(), customer.getEmail(), customer.getProviderId(), customer.isCompany(),
                customer.getPhoneNumber(), addressResource);
    }

    private AddressResource createAddressResource(Address address) {
        return new AddressResource(address.getStreet(), address.getNumber(),
                address.getZipCode(), address.getCity(), address.getCountry());
    }
}

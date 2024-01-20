//package com.example.customer.service;
//
//import com.example.customer.dto.AddressDto;
//import com.example.customer.dto.AddressResource;
//import com.example.customer.dto.CustomerResource;
//import com.example.customer.model.Address;
//import com.example.customer.model.Customer;
//import com.example.customer.repository.AddressRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class AddressServiceTest {
//
//    @InjectMocks
//    private AddressServiceImpl addressService;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    private Address address;
//    private Customer customer;
//
//    public void init() {
//        customer = createCustomer(1, "jaak@email.be", "Jaakske", "Jaak", "Aerts", false);
//        address = createAddress();
//    }
//
////    @Test
////    public void getAddressById() {
////        init();
////        when(addressRepository.findById(1)).thenReturn(Optional.ofNullable(address));
////
////        AddressDto foundAddress = addressService.getAddressById(1);
////
////        assertThat(foundAddress.getId()).isEqualTo(address.getId());
////    }
//
//    private Address createAddress() {
//        address = new Address();
//        address.setId(1);
//        address.setCity("City");
//        address.setCountry("Belgium");
//        address.setStreet("Street");
//        address.setNumber("10b");
//        address.setZipCode("3500");
//        return address;
//    }
//
//    private AddressResource createAddressResource(Address address) {
//        return new AddressResource(address.getStreet(), address.getNumber(),
//                address.getZipCode(), address.getCity(), address.getCountry());
//    }
//
////    private Customer createCustomer(int id, String email, String username, String firstName, String lastName, boolean company) {
////        Customer customer = new Customer();
////        customer.setId(id);
////        customer.setEmail(email);
////        customer.setUsername(username);
////        customer.setFirstName(firstName);
////        customer.setLastName(lastName);
////        customer.setProviderId("100");
////        customer.setCompany(company);
////        customer.setAddressId(1);
////        return customer;
////    }
//
//    private CustomerResource createCustomerResource(Customer customer, AddressResource addressResource) {
//        return new CustomerResource(customer.getUsername(), customer.getFirstName(),
//                customer.getLastName(), customer.getEmail(), customer.getProviderId(), customer.isCompany(),
//                customer.getPhoneNumber(), addressResource);
//    }
//}

//package com.example.customer.service;
//
//import com.example.customer.dto.CustomerDto;
//import com.example.customer.dto.CustomerResource;
//import com.example.customer.model.Customer;
//import com.example.customer.model.QCustomer;
//import com.example.customer.repository.CustomerRepository;
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.types.Predicate;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CustomerServiceTest {
//
//    @InjectMocks
//    private CustomerServiceImpl customerService;
//
//    @Mock
//    private CustomerRepository customerRepository;
//    @Mock
//    private CustomerPageRepository customerPageRepository;
//
//    private Predicate predicate;
//    private BooleanBuilder builder;
//    private Customer customerJaak;
//    private Customer customerMarc;
//    private Customer customerJos;
//
//    public void init() {
//        builder = new BooleanBuilder();
//        customerJaak = createCustomer(1, "jaak@email.be", "Jaakske", "Jaak", "Aerts", false);
//        customerMarc = createCustomer(2, "marc@email.be", "Marcske", "Marc", "Vandenbroek", false);
//        customerJos = createCustomer(2, "jos@email.be", "Joske", "Jos", "Uitdenbroek", true);
//    }
//
//    @Test
//    public void getCustomerByIdTest() {
//        init();
//        when(customerRepository.findCustomerByIdAndProviderId(customerJaak.getId(), customerJaak.getProviderId())).thenReturn(Optional.of(customerJaak));
//
//        CustomerDto foundCustomer = customerService.getCustomerById(customerJaak.getId(), customerJaak.getProviderId());
//
//        assertThat(foundCustomer.getUsername()).isEqualTo(customerJaak.getUsername());
//    }
//
//    @Test
//    public void getCustomerByEmailTest() {
//        init();
//        when(customerRepository.findCustomerByEmailAndProviderId(customerJaak.getEmail(), customerJaak.getProviderId())).thenReturn(Optional.of(customerJaak));
//
//        CustomerDto foundCustomer = customerService.getCustomerByEmail(customerJaak.getEmail(), customerJaak.getProviderId());
//
//        assertThat(foundCustomer.getUsername()).isEqualTo(customerJaak.getUsername());
//    }
//
//    @Test
//    public void getAllCustomersByProvider() {
//        init();
//        List<Customer> customers = new LinkedList<>();
//        customers.add(customerJaak);
//        customers.add(customerJos);
//        customers.add(customerMarc);
//        Page<Customer> customerPage = new PageImpl<>(customers);
//        Pageable paging = PageRequest.of(0, 5);
//
//        when(customerPageRepository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(customerPage);
//        Page<CustomerDto> foundCustomersPage = customerService.getAllCustomers(predicate, "100", paging.getPageNumber(), paging.getPageSize(), "username");
//        List<CustomerDto> foundCustomersList = foundCustomersPage.get().toList();
//
//        assertThat(foundCustomersList.size()).isEqualTo(3);
//        assertThat(foundCustomersList.get(0).getUsername()).isEqualTo(customerJaak.getUsername());
//    }
//
//    private Customer createCustomer(int id, String email, String username, String firstName, String lastName, boolean company) {
//        Customer customer = new Customer();
//        customer.setId(id);
//        customer.setEmail(email);
//        customer.setUsername(username);
//        customer.setFirstName(firstName);
//        customer.setLastName(lastName);
//        customer.setProviderId("100");
//        customer.setCompany(company);
//        customer.setAddressId(1);
//        return customer;
//    }
//}

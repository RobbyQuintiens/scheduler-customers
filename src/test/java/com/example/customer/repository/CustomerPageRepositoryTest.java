//package com.example.customer.repository;
//
//import com.example.customer.dto.CustomerDto;
//import com.example.customer.model.Address;
//import com.example.customer.model.Customer;
//import com.example.customer.model.QCustomer;
//import com.example.customer.service.CustomerPageRepository;
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.types.Predicate;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@Testcontainers
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ImportAutoConfiguration(RefreshAutoConfiguration.class)
//public class CustomerPageRepositoryTest {
//
//    Predicate predicate;
//    @Autowired
//    private CustomerPageRepository customerPageRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private AddressRepository addressRepository;
//
//    private Customer customer;
//    private Customer customer2;
//    private Customer customer3;
//    private Address address;
//
//    @Test
//    public void showAllCustomersTest() {
//        createCustomer();
//        Pageable paging = PageRequest.of(0, 5);
//        Page<CustomerDto> customers = customerPageRepository.findAll(paging).map(CustomerDto::new);
//
//        List<CustomerDto> customerDtos = customers.get().toList();
//
//        assertThat(customers).isNotEmpty();
//        assertThat(customerDtos.get(0).getFirstName()).isEqualTo(customer.getFirstName());
//    }
//
//    @Test
//    public void showAllCustomersForProviderTest() {
//        createCustomer();
//        createSecondCustomer();
//
//        Pageable paging = PageRequest.of(0, 5);
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(QCustomer.customer.providerId.eq(customer.getProviderId()));
//
//        Page<CustomerDto> customerDtos = customerPageRepository.findAll(builder.and(predicate), paging).map(CustomerDto::new);
//
//        List<CustomerDto> customerDtoList = customerDtos.get().toList();
//
//        assertThat(customerDtoList).isNotEmpty();
//        assertThat(customerDtoList.size()).isEqualTo(1);
//        assertThat(customerDtoList.get(0).getUsername()).isEqualTo(customer.getUsername());
//    }
//
//    @Test
//    public void showCustomerByIdAndProviderIdTest() {
//        createSecondCustomer();
//        createThirdCustomer();
//
//        Pageable paging = PageRequest.of(0, 5);
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(QCustomer.customer.providerId.eq(customer2.getProviderId())).and(QCustomer.customer.id.eq(customer2.getId()));
//
//        Page<CustomerDto> customerDtos = customerPageRepository.findAll(builder.and(predicate), paging).map(CustomerDto::new);
//
//        List<CustomerDto> customerDtoList = customerDtos.get().toList();
//
//        assertThat(customerDtoList).isNotEmpty();
//        assertThat(customerDtoList.size()).isEqualTo(1);
//        assertThat(customerDtoList.get(0).getUsername()).isEqualTo(customer2.getUsername());
//    }
//
//    @Test
//    public void showAllCustomersForProviderAndCompanyTrueTest() {
//        createCustomer();
//        createSecondCustomer();
//
//        QCustomer qCustomer = QCustomer.customer;
//        BooleanExpression filterPredicate = qCustomer.username.eq("username").and(qCustomer.company.isTrue());
//
//        Pageable paging = PageRequest.of(0, 5);
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(QCustomer.customer.providerId.eq(customer.getProviderId()));
//
//        Page<CustomerDto> customerDtos = customerPageRepository.findAll(builder.and(filterPredicate), paging).map(CustomerDto::new);
//
//        List<CustomerDto> customerDtoList = customerDtos.get().toList();
//
//        assertThat(customerDtoList).isNotEmpty();
//        assertThat(customerDtoList.size()).isEqualTo(1);
//        assertThat(customerDtoList.get(0).getUsername()).isEqualTo(customer.getUsername());
//    }
//
//    @Test
//    public void showCustomerByEmailAndProviderIdTest() {
//        createSecondCustomer();
//        createThirdCustomer();
//
//        Pageable paging = PageRequest.of(0, 5);
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(QCustomer.customer.providerId.eq(customer2.getProviderId())).and(QCustomer.customer.email.eq(customer2.getEmail()));
//
//        Page<CustomerDto> customerDtos = customerPageRepository.findAll(builder.and(predicate), paging).map(CustomerDto::new);
//
//        List<CustomerDto> customerDtoList = customerDtos.get().toList();
//
//        assertThat(customerDtoList).isNotEmpty();
//        assertThat(customerDtoList.size()).isEqualTo(1);
//        assertThat(customerDtoList.get(0).getUsername()).isEqualTo(customer2.getUsername());
//    }
//
//    private void createCustomer() {
//        customer = new Customer();
//        customer.setId(1);
//        customer.setEmail("email@test.be");
//        customer.setUsername("username");
//        customer.setFirstName("first name");
//        customer.setLastName("last name");
//        customer.setProviderId("100");
//        customer.setCompany(true);
//        customer.setId(1);
//        customer.setAddressId(1);
//        customerRepository.save(customer);
//    }
//
//    private void createSecondCustomer() {
//        customer2 = new Customer();
//        customer2.setEmail("second@test.be");
//        customer2.setUsername("second");
//        customer2.setFirstName("first");
//        customer2.setLastName("last name");
//        customer2.setProviderId("101");
//        customer2.setCompany(false);
//        customer2.setId(2);
//        customer2.setAddressId(2);
//        customerRepository.save(customer2);
//    }
//
//    private void createThirdCustomer() {
//        customer3 = new Customer();
//        customer3.setEmail("third@test.be");
//        customer3.setUsername("third");
//        customer3.setFirstName("first");
//        customer3.setLastName("last name");
//        customer3.setProviderId("101");
//        customer3.setCompany(false);
//        customer3.setId(3);
//        customer3.setAddressId(3);
//        customerRepository.save(customer3);
//    }
//}

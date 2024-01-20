package com.example.customer.controller;

import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.CustomerResource;
import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import com.example.customer.service.UserDetailsFilter;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(defaultValue = "username") String sort,
                                                             @RequestHeader HttpHeaders token,
                                                             @QuerydslPredicate(root = Customer.class) Predicate predicate) {
        String providerId = UserDetailsFilter.getUserInfo(token, "sub");
        return ResponseEntity.ok(customerService.getAllCustomers(predicate, providerId, page, size, sort));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCustomer(@RequestHeader HttpHeaders token,
                                               @RequestBody CustomerResource customerResource) {
        customerService.createCustomer(customerResource, UserDetailsFilter.getUserInfo(token, "sub"));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

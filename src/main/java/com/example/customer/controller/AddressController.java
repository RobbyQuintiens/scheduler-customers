package com.example.customer.controller;

import com.example.customer.dto.AddressDto;
import com.example.customer.dto.AddressResource;
import com.example.customer.service.AddressService;
import com.example.customer.service.UserDetailsFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/{customerId}")
    public ResponseEntity<List<AddressDto>> getCustomerAddress(@PathVariable Integer customerId, @RequestHeader HttpHeaders token) {
        String providerId = UserDetailsFilter.getUserInfo(token, "sub");
        return ResponseEntity.ok(addressService.getAddressById(customerId, providerId));
    }

    @PostMapping("/add/address/{customerId}")
    public ResponseEntity<Void> addAddressToCustomer(@PathVariable Integer customerId, @RequestHeader HttpHeaders token,
                                                     @RequestBody AddressResource addressResource) {
        String providerId = UserDetailsFilter.getUserInfo(token, "sub");
        addressService.addAddressToCustomer(addressResource, customerId, providerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
package com.example.controller;


import com.example.dto.CustomerDTO;
import com.example.entity.Animal;
import com.example.entity.Customer;
import com.example.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers") // http://localhost:8080/customers
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping // http://localhost:8080/customers + POST + JSON
    public ResponseEntity<Map<String,String>> createCustomer(@Valid @RequestBody Customer customer){
        customerService.createCustomer(customer);
        Map<String,String> response = new HashMap<>();
        response.put("message","customer is crated successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }

    @GetMapping // http://localhost:8080/customers + GET
    private ResponseEntity<List<Customer>> getAllCustomer(){
         List<Customer> customers = customerService.findAll();
         return ResponseEntity.ok(customers);
    }

    @DeleteMapping //http://localhost:8080/customer?id=1 + DELETE
    public ResponseEntity<Map<String,String>> deleteCustomer(@RequestParam Long id){
        customerService.deleteCustomer(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","customer is deleted successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}") //http://localhost:8080/customer/1 + UPDATE + JSON
    public ResponseEntity<Map<String,String>> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(id,customerDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message","customer is updated successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }




}

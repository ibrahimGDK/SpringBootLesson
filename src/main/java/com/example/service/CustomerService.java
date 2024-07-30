package com.example.service;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.exception.ConflictException;
import com.example.exception.ResourceNotFoundExcepiton;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AnimalService animalService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,AnimalService animalService) {
        this.customerRepository = customerRepository;
        this.animalService=animalService;
    }


    public void createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())){
            throw new ConflictException("Email already exist");
        } else if (customerRepository.existsByPhone(customer.getPhone())) {
            throw new ConflictException("Phone already exist");
        }
        customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findCustomer(Long id){
       return customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepiton("Customer not found !"));
    }

    public void deleteCustomer(Long id) {
        Customer customer =findCustomer(id);
        customerRepository.delete(customer);
    }

    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = findCustomer(id);

        if (customerDTO.getEmail() != null && !customerDTO.getEmail().equals(customer.getEmail())) {
            boolean emailExists = customerRepository.existsByEmail(customerDTO.getEmail());
            if (emailExists) {
                throw new ConflictException("Email already exists");
            }
        }

        if (customerDTO.getPhone() != null && !customerDTO.getPhone().equals(customer.getPhone())) {
            boolean phoneExists = customerRepository.existsByPhone(customerDTO.getPhone());
            if (phoneExists) {
                throw new ConflictException("Phone number already exists");
            }
        }
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setName(customerDTO.getName());
        customer.setCity(customerDTO.getCity());
        customer.setAddress(customerDTO.getAddress());
        customerRepository.save(customer);
    }
}

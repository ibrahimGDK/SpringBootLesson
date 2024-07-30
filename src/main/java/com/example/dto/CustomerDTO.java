package com.example.dto;


import com.example.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {


    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;


    public CustomerDTO(Customer customer) {
        this.name= customer.getName();
        this.phone= customer.getPhone();
        this.email= customer.getEmail();
        this.address= customer.getAddress();
        this.city=customer.getCity();
    }
}

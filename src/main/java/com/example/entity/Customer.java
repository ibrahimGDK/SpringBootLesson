package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name can not be null")
    @Column(nullable = false,length = 100)
    private String name;
    @NotNull(message = "phone can not be null")
    @Column(nullable = false,length = 100,unique = true)
    private String phone;

    @NotNull(message = "email can not be null")
    @Column(nullable = false, length = 100, unique = true)
    @Email(message = "provide valid email")
    private String email;

    @NotNull(message = "address can not be null")
    @Column(nullable = false,length = 150)
    private String address;

    @NotNull(message = "city can not be null")
    @Column(nullable = false,length = 100)
    private String city;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Animal> animals = new ArrayList<>();


}

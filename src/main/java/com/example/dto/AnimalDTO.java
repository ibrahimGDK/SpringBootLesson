package com.example.dto;

import com.example.entity.Animal;
import com.example.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;

    private Customer customer;

    public AnimalDTO(Animal animal) {
        this.name= animal.getName();
        this.species= animal.getSpecies();
        this.breed= animal.getBreed();
        this.gender= animal.getGender();
        this.colour=animal.getColour();
        this.dateOfBirth=animal.getDateOfBirth();
        this.customer=animal.getCustomer();
    }
}

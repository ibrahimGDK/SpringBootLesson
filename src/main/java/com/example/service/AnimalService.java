package com.example.service;

import com.example.dto.AnimalDTO;
import com.example.entity.Animal;
import com.example.entity.Customer;
import com.example.exception.ResourceNotFoundExcepiton;
import com.example.repository.AnimalRepository;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Autowired
    private CustomerRepository customerRepository;


    public Animal createAnimal(Animal animal) {
        if (animal.getCustomer()!=null){
            Customer customer = customerRepository.findById(animal.getCustomer().getId())
                    .orElseThrow(() -> new ResourceNotFoundExcepiton("Customer not found"));
            animal.setCustomer(customer);
        }
       return animalRepository.save(animal);
    }


    public List<Animal> findByName(String name) {
       return animalRepository.findByNameContainingIgnoreCase(name);
    }




    public Animal findAnimal(Long id){
        return animalRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepiton("Animal not found !"));
    }

    public void deleteAnimal(Long id) {
        Animal animal =findAnimal(id);
        animalRepository.delete(animal);
    }


    public Animal updateAnimal(Long id, AnimalDTO animalDTO) {
        Animal animal = findAnimal(id);

        animal.setName(animalDTO.getName());
        animal.setSpecies(animalDTO.getSpecies());
        animal.setBreed(animalDTO.getBreed());
        animal.setGender(animalDTO.getGender());
        animal.setColour(animalDTO.getColour());
        animal.setDateOfBirth(animalDTO.getDateOfBirth());

        if (animalDTO.getCustomer() != null && animalDTO.getCustomer().getId() != null) {
            Customer customer = customerRepository.findById(animalDTO.getCustomer().getId())
                    .orElseThrow(() -> new ResourceNotFoundExcepiton("Customer not found"));
            animal.setCustomer(customer);
        } else {
            animal.setCustomer(null);
        }

        return animalRepository.save(animal);
    }



    public List<Animal> findAll() {
       return animalRepository.findAll();
    }

    public List<Animal> findByCustomerId(Long customerId) {
        Customer customer =customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundExcepiton("Customer not found"));
       return animalRepository.findByCustomerId(customer.getId());
    }




}

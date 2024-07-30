package com.example.controller;

import com.example.dto.AnimalDTO;
import com.example.entity.Animal;
import com.example.entity.Customer;
import com.example.exception.ResourceNotFoundExcepiton;
import com.example.repository.CustomerRepository;
import com.example.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animals") // http://localhost:8080/animals
public class AnimalController {
    private final AnimalService animalService;
    private final CustomerRepository customerRepository;
    @Autowired
    public AnimalController(AnimalService animalService,CustomerRepository customerRepository) {
        this.animalService = animalService;
        this.customerRepository = customerRepository;
    }

    @PostMapping // http://localhost:8080/animals + POST + JSON
    public ResponseEntity<Map<String,String>> createAnimal(@Valid @RequestBody Animal animal){
        if (animal.getCustomer()!=null && animal.getCustomer().getId()!=null ){
            Customer customer = customerRepository.findById(animal.getCustomer().getId())
                    .orElseThrow(() -> new ResourceNotFoundExcepiton("Customer not found"));
            animal.setCustomer(customer);
        }

        Animal createdAnimal =animalService.createAnimal(animal);
        Map<String,String> response = new HashMap<>();
        response.put("message","animal is crated successfully");
        response.put("status","true");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping() //http://localhost:8080/animals + GET
    public ResponseEntity<List<Animal>> getAllAnimals(){
        List<Animal> animals = animalService.findAll();
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/search")  //http://localhost:8080/animals/search?name=bulut + GET
    public ResponseEntity<List<Animal>> getAnimalByName(@RequestParam("name") String name){
        List <Animal> animalList = animalService.findByName(name);
        if (animalList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animalList);
    }

    @DeleteMapping("/{id}") //http://localhost:8080/animals/1 + DELETE
    public ResponseEntity<Map<String,String>> deleteAnimal (@PathVariable Long id){

        animalService.deleteAnimal(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","animal is deleted successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}") //http://localhost:8080/animals/1 + PUT + JSON
    public ResponseEntity<Map<String,String>> updateAnimal(@PathVariable Long id, @RequestBody AnimalDTO animalDTO){
        Animal updatedAnimal =animalService.updateAnimal(id,animalDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message","animal is updated successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/customer/{customerId}") //http://localhost:8080/animals/customer/1 + GET
    public ResponseEntity<List<Animal>> getAnimalByCustomerId(@PathVariable Long customerId){
        List<Animal> animals = animalService.findByCustomerId(customerId);
        return ResponseEntity.ok(animals);
    }



    




}

package com.example.repository;

import com.example.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {

    List<Animal> findByNameContainingIgnoreCase(String name);


    List<Animal> findByCustomerId(Long id);
}

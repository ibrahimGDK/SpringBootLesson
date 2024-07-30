package com.example.repository;

import com.example.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Long> {

    boolean existsByCode(String code);

    List<Vaccine> findByAnimalId(Long id);


    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate);

    List<Vaccine> findByAnimalIdAndNameAndCode(Long id, String name, String code);
}

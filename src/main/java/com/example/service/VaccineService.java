package com.example.service;

import com.example.dto.VaccineDTO;
import com.example.entity.Animal;
import com.example.entity.Vaccine;
import com.example.exception.ConflictException;
import com.example.exception.ResourceNotFoundExcepiton;
import com.example.repository.AnimalRepository;
import com.example.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    public VaccineService(VaccineRepository vaccineRepository,AnimalRepository animalRepository) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository=animalRepository;
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }


    public void createVaccine(VaccineDTO vaccineDTO) {


        if (vaccineRepository.existsByCode(vaccineDTO.getCode())){
            throw new ConflictException("Vaccine code already exists");
        }

        if (isVaccineConflict(vaccineDTO)){
            throw new ConflictException("The vaccine protection deadline is not yet available, the new vaccine cannot be entered");
        }


        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineDTO.getName());
        vaccine.setCode(vaccineDTO.getCode());
        Animal animal = animalRepository.findById(vaccineDTO.getAnimal().getId())
                .orElseThrow(() -> new ResourceNotFoundExcepiton("Animal not found"));

        vaccine.setAnimal(vaccineDTO.getAnimal());
        vaccine.setProtectionStartDate(vaccineDTO.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineDTO.getProtectionFinishDate());
        vaccineRepository.save(vaccine);

    }



    public void deleteVaccine(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepiton("Vaccine not found"));
        vaccineRepository.delete(vaccine);
    }

    public void updateVaccine(Long id, VaccineDTO vaccineDTO) {

        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcepiton("Vaccine not found"));
        if (vaccineRepository.existsByCode(vaccineDTO.getCode())){
            throw new ConflictException("vaccine code alrady exist");
        }
        vaccine.setName(vaccineDTO.getName());
        vaccine.setCode(vaccineDTO.getCode());
        vaccine.setProtectionStartDate(vaccineDTO.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineDTO.getProtectionFinishDate());
        vaccine.setAnimal(vaccineDTO.getAnimal());

        vaccineRepository.save(vaccine);
    }

    public List<Vaccine> getVaccineByAnimalId(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundExcepiton("Animal not found"));
       return vaccineRepository.findByAnimalId(animal.getId());
    }

    public boolean isVaccineConflict(VaccineDTO vaccineDTO){
        List<Vaccine> existingVaccines = vaccineRepository.findByAnimalId(vaccineDTO.getAnimal().getId());
        for (Vaccine existingVaccine:existingVaccines) {
            if (existingVaccine.getName().equals(vaccineDTO.getName()) &&
                    existingVaccine.getCode().equals(vaccineDTO.getCode()) &&
                existingVaccine.getProtectionFinishDate().isAfter(LocalDate.now())){
                return true;
            }
        }
        return false;
    }

    public List<Vaccine> getVaccinesByProtectionFinishDateRange(LocalDate startDate, LocalDate finishDate) {

      return   vaccineRepository.findByProtectionFinishDateBetween(startDate,finishDate);
    }
}

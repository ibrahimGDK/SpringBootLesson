package com.example.controller;

import com.example.dto.VaccineDTO;
import com.example.entity.Vaccine;
import com.example.service.VaccineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vaccines") // http://localhost:8080/vaccines
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }


    @GetMapping // http://localhost:8080/vaccines + GET
    private ResponseEntity<List<Vaccine>> getAllVaccines(){
        List<Vaccine> vaccineList = vaccineService.getAllVaccines();
        return ResponseEntity.ok(vaccineList);
    }

    @PostMapping // http://localhost:8080/vaccines + POST + JSON
    public ResponseEntity<Map<String,String>> createVaccine(@Valid @RequestBody VaccineDTO vaccineDTO){
        vaccineService.createVaccine(vaccineDTO);

        Map<String,String> response = new HashMap<>();
        response.put("message","vaccine is created successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}") // http://localhost:8080/vaccines/1 + DELETE
    public ResponseEntity<Map<String,String>> deleteVaccine(@PathVariable Long id){

        vaccineService.deleteVaccine(id);

        Map<String,String> response = new HashMap<>();
        response.put("message","vaccine is deleted successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}") // http://localhost:8080/vaccines/1 POST + JSON
    public ResponseEntity<Map<String, String>> updateVaccine(@PathVariable Long id, @Valid @RequestBody VaccineDTO vaccineDTO) {
        vaccineService.updateVaccine(id, vaccineDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Vaccine is updated successfully");
        response.put("status", "true");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/query/{animalId}") // http://localhost:8080/vaccines/query/1 + GET
    public ResponseEntity<List<Vaccine>> getVaccineByAnimalId(@PathVariable Long animalId){
        List<Vaccine> vaccines = vaccineService.getVaccineByAnimalId(animalId);
        return ResponseEntity.ok(vaccines);
    }

    @GetMapping("/by-protection-finish-date") //http://localhost:8080/vaccines/by-protection-finish-date?startDate=2024-05-01&finishDate=2024-05-31 + GET
    public ResponseEntity<List<Vaccine>> getVaccinesByProtectionFinishDateRange(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("finishDate") String finishDateStr){

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate finishDate = LocalDate.parse(finishDateStr);

        List<Vaccine> vaccines = vaccineService.getVaccinesByProtectionFinishDateRange(startDate,finishDate);
        return ResponseEntity.ok(vaccines);
    }
}

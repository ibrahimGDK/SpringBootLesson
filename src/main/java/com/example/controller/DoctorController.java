package com.example.controller;

import com.example.dto.DoctorDTO;
import com.example.entity.Doctor;
import com.example.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctors") // http://localhost:8080/doctors
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping //http://localhost:8080/doctors + POST + JSON
    public ResponseEntity<Map<String,String>> createDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
        doctorService.createDoctor(doctorDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message","doctor is created successfully");
        response.put("status","true");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping //http://localhost:8080/doctors + GET
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @DeleteMapping //http://localhost:8080/doctors?id=1 + DELETE
    public ResponseEntity<Map<String,String>> deleteDoctor(@RequestParam("id") Long id){
        doctorService.deleteDoctor(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","doctor is deleted successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}") //http://localhost:8080/doctors/1 + JSON + PUT
    public ResponseEntity<Map<String,String>> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDTO doctorDTO){
        Doctor updateDoctor = doctorService.updateDoctor(id,doctorDTO);

        Map<String,String> response = new HashMap<>();
        response.put("message","doctor is updated successfully");
        response.put("status","true");
        return ResponseEntity.ok(response);
    }

}

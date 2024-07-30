package com.example.controller;

import com.example.dto.AvailableDateDTO;
import com.example.entity.AvailableDate;
import com.example.service.AvailableDateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/availableDates")  // http://localhost:8080/availableDates
public class AvailableDateController {
   private final AvailableDateService availableDateService;

    public AvailableDateController(AvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping // http://localhost:8080/availableDates + PUT + JSON
    public ResponseEntity<Map<String,String>> createAvailableDate(@Valid @RequestBody AvailableDateDTO availableDateDTO) {
        AvailableDate createdAvailableDate = availableDateService.createAvailableDate(availableDateDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message","animal is crated successfully");
        response.put("status","true");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping // http://localhost:8080/availableDates + GET
    public ResponseEntity<List<AvailableDate>> getAllAvailableDate(){
        List<AvailableDate> availableDates = availableDateService.getAllAvailableDate();
        return ResponseEntity.ok(availableDates);
    }

    @DeleteMapping("/{id}") // http://localhost:8080/availableDates/1 + DELETE
    public ResponseEntity<Map<String,String>> deleteAvailableDate(@PathVariable("id") Long id){
        availableDateService.deleteAvailableDate(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","availabe date is deleted successfully");
        response.put("status","true");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping // http://localhost:8080/availableDates?id=1 + PUT + JSON
    public ResponseEntity<Map<String,String>> updateAvailableDate(@RequestParam Long id, @Valid @RequestBody AvailableDateDTO availableDateDTO){
        AvailableDate updatedAvailableDate  = availableDateService.updateAvailableDate(id,availableDateDTO);

        Map<String,String> response = new HashMap<>();
        response.put("message","availabe date is updated successfully");
        response.put("status","true");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

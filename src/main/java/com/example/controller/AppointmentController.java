package com.example.controller;

import com.example.dto.AppointmentDTO;
import com.example.entity.Appointment;
import com.example.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments") // http://localhost:8080/appointments
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping // http://localhost:8080/appointments + POST + JSON
    public ResponseEntity<Map<String,String>> createAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO){

        appointmentService.createAppointment(appointmentDTO);


        Map<String,String> response = new HashMap<>();
        response.put("message","appointment is created successfully");
        response.put("status","true");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping // http://localhost:8080/appointments + GET
    public ResponseEntity<List<AppointmentDTO>> getAllAppointment(){
        List<AppointmentDTO> appointmentDTOList = appointmentService.getAllAppointment();
        return ResponseEntity.ok(appointmentDTOList);
    }

    @DeleteMapping("/{id}") // http://localhost:8080/appointments/1 + DELETE
    public ResponseEntity<Map<String,String>> deleteAppointment(@PathVariable Long id){

        appointmentService.deleteAppointmert(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","appointment is deleted successfully");
        response.put("status","true");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}") // http://localhost:8080/appointments/1 + PUT + JSON
    public ResponseEntity<Map<String,String>> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentDTO appointmentDTO){

        appointmentService.updateAppointment(id,appointmentDTO);

        Map<String,String> response = new HashMap<>();
        response.put("message","appointment is updated successfully");
        response.put("status","true");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/query") // http://localhost:8080/appointments/query?animalId=11&startDate=2024-08-01T11:30:00&endDate=2024-08-01T23:59:59
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByAnimalAndDateRange(
                                                                            @RequestParam Long animalId,
                                                                            @RequestParam String startDate,
                                                                            @RequestParam String endDate  ){

        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);


        List<AppointmentDTO> appointmentDTO = appointmentService.getAppointmentsByAnimalAndDateRange(animalId,startDateTime,endDateTime);
        return ResponseEntity.ok(appointmentDTO);
    }

    @GetMapping("/doctor-date-range") // http://localhost:8080/appointments/doctor-date-range?doctorId=4&startDate=2024-08-01T12:00:00&endDate=2024-08-01T13:59:59
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctorAndDateRange(@RequestParam Long doctorId,
                                                                                    @RequestParam String startDate,
                                                                                    @RequestParam String endDate){
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);

        List<AppointmentDTO> appointmentDTOList = appointmentService.getAppointmentsByDoctorAndDateRange(doctorId,startDateTime,endDateTime);
        return ResponseEntity.ok(appointmentDTOList);

    }



}

package com.example.dto;

import com.example.entity.Animal;
import com.example.entity.Appointment;
import com.example.entity.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDTO {



    private LocalDateTime appointmentDate ;

    private Animal animal;

    private Doctor doctor;


    public AppointmentDTO(Appointment appointment) {
        this.appointmentDate= appointment.getAppointmentDate();
        this.animal= appointment.getAnimal();
        this.doctor=appointment.getDoctor();
    }

}

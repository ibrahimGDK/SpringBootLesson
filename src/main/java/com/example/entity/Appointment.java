package com.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private LocalDateTime appointmentDate ;


    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;






}

package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private LocalDate availableDate;


    /*@OneToMany(mappedBy = "availableDates")
    private List< Appointment > appointments = new ArrayList<>();
*/
    /*@OneToMany(mappedBy = "availableDates", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();*/
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false,unique = true)

    private Doctor doctor;



}

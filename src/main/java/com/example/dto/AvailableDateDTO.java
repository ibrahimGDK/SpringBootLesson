package com.example.dto;

import com.example.entity.AvailableDate;
import com.example.entity.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateDTO {
    private LocalDate availableDate;

    @Column(unique = true)
    private Doctor doctor;


    public AvailableDateDTO(AvailableDate availableDate) {
        this.availableDate=availableDate.getAvailableDate();
        this.doctor=availableDate.getDoctor();
    }
}

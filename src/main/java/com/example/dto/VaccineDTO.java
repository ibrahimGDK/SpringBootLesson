package com.example.dto;

import com.example.entity.Animal;
import com.example.entity.Vaccine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDTO {




    @NotNull(message = "name can not be null")
    @Column(nullable = false,length = 100)
    private String name;

    @NotNull(message = "code can not be null")
    @Column(nullable = false,length = 100,unique = true)
    private String code;


    @Column(nullable = false)
    private LocalDate protectionStartDate ;


    @Column(nullable = false)
    private LocalDate protectionFinishDate ;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public VaccineDTO (Vaccine vaccine) {
        this.name= vaccine.getName();
        this.code=vaccine.getCode();
        this.protectionStartDate=vaccine.getProtectionStartDate();
        this.protectionFinishDate=vaccine.getProtectionFinishDate();
        this.animal=vaccine.getAnimal();
    }
}

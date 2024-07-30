package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


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



}

package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "name can not be null")
    @Column(nullable = false,length = 100)
     private String name;
    @NotNull(message = "species can not be null")
    @Column(nullable = false,length = 100)
     private String species;

    @NotNull(message = "breed can not be null")
    @Column(nullable = false,length = 100)
     private String breed;

    @NotNull(message = "gender can not be null")
    @Column(nullable = false,length = 100)
     private String gender;

    @NotNull(message = "colour can not be null")
    @Column(nullable = false,length = 100)
     private String colour;


    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Vaccine> vaccines = new ArrayList<>();

    @OneToMany(mappedBy = "animal",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointments;

}

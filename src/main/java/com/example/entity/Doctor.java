package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "name can not be null")
    @Column(nullable = false,length = 100)
    private String name;
    @NotNull(message = "phone can not be null")
    @Column(nullable = false,length = 100,unique = true)
    private String phone;


    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "provide valid email")
    private String email;

    @NotNull(message = "address can not be null")
    @Column(nullable = false,length = 150)
    private String address;

    @NotNull(message = "city can not be null")
    @Column(nullable = false,length = 100)
    private String city;



 /*   @ManyToMany
    @JoinTable(
            name = "doctor_availabeDate_tbl",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "availableDate_id")
    )
    private List<AvailableDate> availableDates = new ArrayList<>();
*/

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<AvailableDate> availableDates;


}

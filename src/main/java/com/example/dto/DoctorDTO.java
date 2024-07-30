package com.example.dto;

import com.example.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {


    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;

    public DoctorDTO(Doctor doctor) {
        this.name= doctor.getName();
        this.phone= doctor.getPhone();
        this.email= doctor.getEmail();
        this.address= doctor.getAddress();
        this.city=doctor.getCity();
    }


}

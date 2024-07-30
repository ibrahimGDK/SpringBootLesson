package com.example.service;

import com.example.dto.AvailableDateDTO;
import com.example.entity.AvailableDate;
import com.example.entity.Doctor;
import com.example.exception.ResourceNotFoundExcepiton;
import com.example.repository.AvailableDateRepository;
import com.example.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateService {
    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;

    public AvailableDateService(AvailableDateRepository availableDateRepository,DoctorRepository doctorRepository) {
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository=doctorRepository;
    }




    public void deleteAvailableDate(Long id) {
        AvailableDate availableDate = availableDateRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExcepiton("Available Date not found"));
        availableDateRepository.delete(availableDate);
    }

    public AvailableDate createAvailableDate(AvailableDateDTO availableDateDTO) {
        Doctor doctor = doctorRepository.findById(availableDateDTO.getDoctor().getId()).orElseThrow(
                ()-> new ResourceNotFoundExcepiton("Doctor not found"));


        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailableDate(availableDateDTO.getAvailableDate());
        availableDate.setDoctor(availableDateDTO.getDoctor());
        return availableDateRepository.save(availableDate);
    }

    public List<AvailableDate> getAllAvailableDate() {
        return availableDateRepository.findAll();
    }


    public AvailableDate updateAvailableDate(Long id, AvailableDateDTO availableDateDTO) {

       AvailableDate availableDate = availableDateRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExcepiton("Available Date already exist"));


        availableDate.setDoctor(availableDateDTO.getDoctor());
        availableDate.setAvailableDate(availableDateDTO.getAvailableDate());

       return availableDateRepository.save(availableDate);

    }
}

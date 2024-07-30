package com.example.service;

import com.example.dto.DoctorDTO;
import com.example.entity.Doctor;
import com.example.exception.ConflictException;
import com.example.exception.ResourceNotFoundExcepiton;
import com.example.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    public void createDoctor(DoctorDTO doctorDTO) {
        if (doctorRepository.existsByEmail(doctorDTO.getEmail())||doctorRepository.existsByPhone(doctorDTO.getPhone())){
            throw new ConflictException("This doctor already exists");
        }
        Doctor doctor = new Doctor();
        doctor.setAddress(doctorDTO.getAddress());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setName(doctorDTO.getName());
        doctor.setCity(doctorDTO.getCity());
        doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findDoctor(Long id){
       return doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepiton("Doctor not found"));
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = findDoctor(id);
        doctorRepository.delete(doctor);
    }

    public Doctor updateDoctor(Long id, DoctorDTO doctorDTO) {
        boolean emailExists = doctorRepository.existsByEmail(doctorDTO.getEmail());
        boolean phoneExists = doctorRepository.existsByPhone(doctorDTO.getPhone());

        Doctor doctor = findDoctor(id);

        if (emailExists && !doctorDTO.getEmail().equals(doctor.getEmail())){
            throw new ConflictException("Email is already exist ");
        } else if (phoneExists && !doctorDTO.getPhone().equals(doctor.getPhone()))  {
            throw new ConflictException("Phone is already exist ");
        }

        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setName(doctorDTO.getName());
        doctor.setCity(doctorDTO.getCity());
        doctor.setAddress(doctorDTO.getAddress());
        return doctorRepository.save(doctor);
    }
}

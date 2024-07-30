package com.example.repository;

import com.example.entity.Appointment;
import com.example.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    boolean existsByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime appointmentDate);

   List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    //  List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, String startDate, String endDate);
}

package com.example.service;

import com.example.dto.AppointmentDTO;
import com.example.entity.Appointment;
import com.example.entity.Doctor;
import com.example.exception.ConflictException;
import com.example.exception.DoctorUnavailableException;
import com.example.exception.ResourceNotFoundExcepiton;
import com.example.repository.AppointmentRepository;
import com.example.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }



    public void createAppointment(AppointmentDTO appointmentDTO) {

        Appointment appointment = new Appointment();

        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctor().getId())
                .orElseThrow(() -> new ResourceNotFoundExcepiton("Doctor not found"));

        boolean isDoctorAvailable = doctor.getAvailableDates().stream()
                .anyMatch(date -> date.getAvailableDate().equals(appointmentDTO.getAppointmentDate().toLocalDate()));

        if (isDoctorAvailable){
            throw new DoctorUnavailableException("Doctor is not available");
        }

        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setDoctor(appointmentDTO.getDoctor());
        appointment.setAnimal(appointmentDTO.getAnimal());
        boolean isConflictAppointment = appointmentRepository.existsByDoctorAndAppointmentDate(appointmentDTO.getDoctor(),appointmentDTO.getAppointmentDate());
            if (isConflictAppointment){
                throw new ConflictException("Appointment already exist");
            }
            appointmentRepository.save(appointment);
        }


        private List<AppointmentDTO> convertToDTO(List<Appointment> appointments){


            List<AppointmentDTO> appointmentDTOList = new ArrayList<>();

            for (Appointment appointment : appointments) {

                appointmentDTOList.add(new AppointmentDTO(appointment));
            }
            return appointmentDTOList;
        }



        public List<AppointmentDTO> getAllAppointment() {

            List<Appointment> appointments = appointmentRepository.findAll();

           return convertToDTO(appointments);
        }


        private Appointment findAppointment(Long id){
            Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundExcepiton("appointment not found"));
            return appointment;
        }

        public void deleteAppointmert(Long id) {
            Appointment appointment = findAppointment(id);
            appointmentRepository.delete(appointment);
        }



        public void updateAppointment(Long id, AppointmentDTO appointmentDTO) {
            Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcepiton("Appointment not found"));

        boolean isConflictAppointment = appointmentRepository.existsByDoctorAndAppointmentDate(
                appointmentDTO.getDoctor(), appointmentDTO.getAppointmentDate());

        if (isConflictAppointment && !appointment.getId().equals(id)) {
            throw new ConflictException("Appointment already exists");
        }

            appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
            appointment.setDoctor(appointmentDTO.getDoctor());
            appointment.setAnimal(appointmentDTO.getAnimal());

            appointmentRepository.save(appointment);
    }

    public List<AppointmentDTO> getAppointmentsByAnimalAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime endDate) {

        List<Appointment> appointments = appointmentRepository.findByAnimalIdAndAppointmentDateBetween(animalId,startDate,endDate);
        if (appointments.isEmpty()) {
            throw new ResourceNotFoundExcepiton("No appointments found for the given animal and date range.");
        }

        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();

        for (Appointment appointment:appointments) {
            if (appointment.getAnimal().getId().equals(animalId)){
                appointmentDTOList.add(new AppointmentDTO(appointment));
            }else {
                throw new ResourceNotFoundExcepiton("No appointments found for the given animal id");
            }
        }
        return appointmentDTOList;
    }


    public List<AppointmentDTO> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundExcepiton("Doctor with ID " + doctorId + " not found."));

        List<Appointment> appointmentList = appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId,startDateTime,endDateTime);

        if (appointmentList.isEmpty()){
            throw new ResourceNotFoundExcepiton("No appointments found for the given animal and date range.");
        }
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();

        for (Appointment appointment :appointmentList) {
            appointmentDTOList.add(new AppointmentDTO(appointment));
        }
        return appointmentDTOList;
    }
}

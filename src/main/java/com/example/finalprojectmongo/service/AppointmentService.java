package com.example.finalprojectmongo.service;

import com.example.finalprojectmongo.model.Appointment;
import com.example.finalprojectmongo.model.UserProfile;
import com.example.finalprojectmongo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private UserProfileService userProfileService;

    public AppointmentService(AppointmentRepository appointmentRepository, UserProfileService userProfileService) {
        this.appointmentRepository = appointmentRepository;
        this.userProfileService = userProfileService;
    }

    public List<Appointment> getAllAppointmentList() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAllDoctorAppointment(String doctorUserName) {
        return appointmentRepository.findAllByDoctorUserName(doctorUserName);
    }

    public Appointment saveAppointment(Appointment appointment) {
        LocalDateTime toBeAssigned = LocalDateTime.of(LocalDate.parse(appointment.getDateString()), LocalTime.of(0, 0, 0));
        appointment.setDateOfAppointment(toBeAssigned);
        System.out.println(appointment);

        UserProfile userByNid = userProfileService.getUserByNid(appointment.getPatientNid());
        appointment.setPatientProfile(userByNid);

        List<Appointment> list = appointmentRepository.findAllByPatientNid(appointment.getPatientNid());
        Optional<Appointment> first = list.stream().filter(app ->
                app.getDateOfAppointment().isEqual(appointment.getDateOfAppointment()) &&
                app.getDoctorUserName().equals(appointment.getDoctorUserName())
        ).findFirst();

        if (first.isPresent()) {
            System.out.println("Appointment already exists.");
            return null;
        }

        return appointmentRepository.save(appointment);
    }
}

package com.example.finalprojectmongo.service;

import com.example.finalprojectmongo.model.Appointment;
import com.example.finalprojectmongo.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceTest {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    AppointmentRepository appointmentRepository;

    @Test
    public void testListAllAppointment() {
        List<Appointment> list = appointmentService.getAllAppointmentList();
        list.forEach(System.out::println);
    }

    @Test
    public void testDeleteAll() {
        appointmentRepository.deleteAll();
    }
}
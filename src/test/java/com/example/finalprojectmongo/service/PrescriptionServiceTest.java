package com.example.finalprojectmongo.service;

import com.example.finalprojectmongo.model.Prescription;
import com.example.finalprojectmongo.repository.PrescriptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PrescriptionServiceTest {
    @Autowired PrescriptionService prescriptionService;
    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Test
    public void testGetAllPrescriptionList() {
        List<Prescription> all = prescriptionRepository.findAll();

        all.forEach(item -> {
            System.out.println(item.getPatient());
            System.out.println(item.getDoctor());
            System.out.println(item.getAppointmentDate());
            System.out.println(item.getListOfMedicine().toString());
        });
    }
}
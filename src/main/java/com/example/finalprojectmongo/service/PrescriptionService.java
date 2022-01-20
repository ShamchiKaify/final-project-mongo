package com.example.finalprojectmongo.service;

import com.example.finalprojectmongo.model.Prescription;
import com.example.finalprojectmongo.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {
    private PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public Prescription addPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> getPrescriptionListByNid(String nid) {
        return prescriptionRepository.findAllByPatient_Nid(nid);
    }
}

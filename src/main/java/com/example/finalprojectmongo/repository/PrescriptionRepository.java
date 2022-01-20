package com.example.finalprojectmongo.repository;

import com.example.finalprojectmongo.model.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
    List<Prescription> findAllByPatient_Nid(String nid);
}

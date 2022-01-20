package com.example.finalprojectmongo.repository;

import com.example.finalprojectmongo.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findAllByPatientNid(String nid);
    List<Appointment> findAllByDoctorUserName(String userName);
}

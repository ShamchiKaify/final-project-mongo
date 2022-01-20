package com.example.finalprojectmongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    @Id @Indexed
    private String id;
    @Indexed
    private UserProfile patient;
    private UserProfile doctor;
    private String appointmentDate;
    private LocalDateTime convertedAppointmentDateTime;
    private List<PrescribedMedicine> listOfMedicine;
}

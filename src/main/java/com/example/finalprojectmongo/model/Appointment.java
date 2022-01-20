package com.example.finalprojectmongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    private String id;
    private String doctorUserName;
    private String patientNid;
    private UserProfile patientProfile;
    private LocalDateTime dateOfAppointment;
    private String dateString;
    private boolean appointmentStatus;
}

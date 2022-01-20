package com.example.finalprojectmongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrescribedMedicine {
    @Id
    private String id;
    private String medicineId;
    private String medicineDosage;
}

package com.example.bamsanteback.Class;

import com.example.bamsanteback.Entities.Patient;
import com.example.bamsanteback.Entities.Rendezvous;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Dashbord {
    private Integer nbPatient;
    private Integer nomTotal;
    private List<Rendezvous> rendezvousList;
    private List<Patient> patientList;
}

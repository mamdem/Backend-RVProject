package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.PatientDao;
import com.example.bamsanteback.Entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PatientController {
    @Autowired
    PatientDao patientDao;

    @PostMapping(value = "/personnes/add")
    public ResponseEntity<Patient> addPersonne (@RequestBody Patient patient)
    {
        Patient patient1 = patientDao.save(patient);
        if(patient1 == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(patient1);
    }

    @GetMapping(value = "/personnes")
    public List<Patient> getPersonnes(){
        return patientDao.findAll();
    }

    @GetMapping(value = "/personnes/{id}")
    public Patient getPersonneById(@PathVariable int id){
        return patientDao.findById(id);
    }

    @PostMapping(value = "/patient/login")
    public Patient login(@RequestBody Patient patient){
        return patientDao.loginUser(patient.getTel(), patient.getMdp());
    }
}

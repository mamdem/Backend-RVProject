package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient,Integer> {
    Patient findById(int id);

    @Query(value = "select * from patient where tel=:tel and mdp=:mdp", nativeQuery = true)
    Patient loginUser(String tel, String mdp);
}

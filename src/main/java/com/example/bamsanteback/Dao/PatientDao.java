package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PatientDao extends JpaRepository<Patient,Integer> {
    Patient findById(int id);

    @Query(value = "select * from patient where tel=:tel and mdp=:mdp", nativeQuery = true)
    Patient loginUser(String tel, String mdp);

    @Modifying
    @Transactional
    @Query(value = "update patient set profil=:profil where idpatient=:idpatient", nativeQuery = true)
    Integer editUrlImage(String profil, Integer idpatient);
}

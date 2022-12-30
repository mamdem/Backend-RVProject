package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedecinDao extends JpaRepository<Medecin,Integer> {
    Medecin findById(int id);

    @Query(value = "select * from medecin where tel=:tel and mdp=:mdp", nativeQuery = true)
    Medecin loginUser(String tel, String mdp);

    @Query(value = "select * from medecin order by rand()", nativeQuery = true)
    List<Medecin> getAllMedecinByRand();


}

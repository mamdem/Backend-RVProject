package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneDao extends JpaRepository<Personne,Integer> {
    Personne findById(int id);
}

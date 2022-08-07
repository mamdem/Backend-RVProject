package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureDao extends JpaRepository<Structure,Integer> {
    Structure findById(int id);
}

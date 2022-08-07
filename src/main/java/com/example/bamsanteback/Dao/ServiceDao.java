package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<Service,Integer> {
    Service findById(int id);
}

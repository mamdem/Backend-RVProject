package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezvousDao extends JpaRepository<Rendezvous,Integer> {
    Rendezvous findById(int id);
}

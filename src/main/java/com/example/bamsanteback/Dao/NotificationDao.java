package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends JpaRepository<Notification,Integer> {
    Notification findById(int id);
}

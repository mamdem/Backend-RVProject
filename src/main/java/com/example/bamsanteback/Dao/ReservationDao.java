package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer> {
    @Query(value = "select * from reservation where idrdv in (select idrdv from rendezvous where idmedecin=:idmedecin) and status !=-1 and status!=1", nativeQuery = true)
    List<Reservation> getAllReservationByMedecin(Integer idmedecin);

    @Query(value = "select * from reservation where idpatient=:idpatient", nativeQuery = true)
    List<Reservation> getPatientRV(Integer idpatient);
}

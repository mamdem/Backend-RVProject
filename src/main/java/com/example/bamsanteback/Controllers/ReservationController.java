package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.PatientDao;
import com.example.bamsanteback.Dao.RendezvousDao;
import com.example.bamsanteback.Dao.ReservationDao;
import com.example.bamsanteback.Dtos.ReservationDTO;
import com.example.bamsanteback.Entities.Rendezvous;
import com.example.bamsanteback.Entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ReservationController {
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private RendezvousDao rendezvousDao;
    @Autowired
    private PatientDao patientDao;

    @GetMapping(value = "/reservation/all")
    List<Reservation> getAllReservation(){return reservationDao.findAll();}

    @GetMapping(value = "/reservation/medecin/{idmedecin}")
    List<Reservation> getAllReservationByMedecin(@PathVariable Integer idmedecin){
        return reservationDao.getAllReservationByMedecin(idmedecin);
    }

    @DeleteMapping(value = "/reservation/{idreservation}/{idpatient}")
    Boolean deleteReservation(@PathVariable Integer idreservation, @PathVariable Integer idpatient){
        reservationDao.deleteReservationByIdrdv(idreservation, idpatient);
        return true;
    }
}

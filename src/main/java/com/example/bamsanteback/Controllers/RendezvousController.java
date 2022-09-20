package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Class.AvailableSend;
import com.example.bamsanteback.Class.Creneaux;
import com.example.bamsanteback.Class.RendezVousSend;
import com.example.bamsanteback.Dao.MedecinDao;
import com.example.bamsanteback.Dao.PatientDao;
import com.example.bamsanteback.Dao.RendezvousDao;
import com.example.bamsanteback.Dao.ReservationDao;
import com.example.bamsanteback.Entities.Medecin;
import com.example.bamsanteback.Entities.Rendezvous;
import com.example.bamsanteback.Entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RendezvousController {
    @Autowired
    private RendezvousDao rendezvousDao;
    @Autowired
    private MedecinDao medecinDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private ReservationDao reservationDao;

    @PostMapping(value = "/rendezvous/add")
    public Boolean addRendezvous (@RequestBody RendezVousSend rendezVousSend) {
        Medecin medecin = medecinDao.findById(rendezVousSend.getIdmedecin());
        for(int i=0;i<rendezVousSend.getCreneaux().size();i++){
            rendezvousDao.save(new Rendezvous(
                    null,
                    rendezVousSend.getDatecreation(),
                    rendezVousSend.getCreneaux().get(i).getHeuredebut(),
                    rendezVousSend.getCreneaux().get(i).getHeurefin(),
                    "Description",
                    0,
                    medecin,
                    null,
                    null
                )
            );
        }
        return true;
    }

    @GetMapping(value = "/rendezvous/available/{idpatient}")
    public List<Rendezvous> getRendezvous(@PathVariable Integer idpatient){
        return rendezvousDao.getAllAvailableRV(idpatient);
    }

    @GetMapping(value = "/rendezvous/{id}")
    public Rendezvous getRendezvousById(@PathVariable int id){
        return rendezvousDao.findById(id);
    }

    @GetMapping("/rendezvous/all/{idmedecin}/{firstdate}/{lastdate}")
    public List<Rendezvous> getRendezBetweenByMedecin(@PathVariable String idmedecin, @PathVariable String firstdate, @PathVariable String lastdate){
        return rendezvousDao.getAllRVBeetweenByMedecin(idmedecin,firstdate, lastdate);
    }

    @GetMapping("/rendezvous/all/patient/{idpatient}/{firstdate}/{lastdate}")
    public List<Rendezvous> getRendezBetween(@PathVariable Integer idpatient,  @PathVariable String firstdate, @PathVariable String lastdate){
        return rendezvousDao.getAllRVBeetween(idpatient,firstdate,lastdate);
    }

    @PostMapping("/rendezvous/available/")
    public List<Creneaux> getSlotsAvalaible(@RequestBody AvailableSend info) throws ParseException {
        List<Creneaux> creneauxList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        String startTime = info.getStartTime();;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        Date d = df.parse(startTime);
        cal.setTime(d);
        cal.add(Calendar.MINUTE, info.getEcartTime());
        String endTime = df.format(cal.getTime());
        List<Creneaux> rendezvousList = rendezvousDao.getAllRVByDatereation(info.getIdmedecin(), info.getDate());

        System.out.println(rendezvousList);
        boolean b=true;
        while(LocalTime.parse("18:00").isAfter(LocalTime.parse(endTime))){
            d = df.parse(startTime);
            cal.setTime(d);
            cal.add(Calendar.MINUTE, info.getEcartTime());
            endTime = df.format(cal.getTime());

            for(Creneaux rv : rendezvousList){
                if (!((LocalTime.parse(startTime).compareTo(LocalTime.parse(rv.getHeuredebut()))<=0
                        && LocalTime.parse(endTime).compareTo(LocalTime.parse(rv.getHeuredebut()))<=0)
                        || LocalTime.parse(startTime).compareTo(LocalTime.parse(rv.getHeurefin()))>=0
                        && LocalTime.parse(endTime).compareTo(LocalTime.parse(rv.getHeurefin()))>=0)){
                    b=false;
                    break;
                }
            }

            if(b)
                creneauxList.add(new Creneaux(startTime, endTime,1));
            b=true;
            startTime = endTime;
        }

        return creneauxList;
    }

    @GetMapping(value = "/rendezvous/action/{idrdv}/{val}/{idpatient}")
    Boolean validerRV(@PathVariable Integer idrdv, @PathVariable Integer val, @PathVariable(required = false) Integer idpatient){
        rendezvousDao.updateRvStatus(idrdv, val, idpatient);
        if(val==1)
            rendezvousDao.valide(idpatient, idrdv);
        return true;
    }

    @GetMapping(value = "/rendezvous/patient/{patient}")
    List<Reservation> getAllPatientRV(@PathVariable Integer patient){
        return reservationDao.getPatientRV(patient);
    }

    @PutMapping(value = "/rendezvous/reserver/{idrdv}/{idpatient}")
    Boolean reserver(@PathVariable Integer idrdv, @PathVariable Integer idpatient){
        rendezvousDao.reserver(idpatient, idrdv);
        return true;
    }

    //Récupération de la liste de tous les rendez confirmé ou rejeté (selon le statut)
    @GetMapping(value = "/rendezvous/status/{status}")
    public List<Rendezvous> getAllConfirmesRV(@PathVariable Integer status){
        return  rendezvousDao.getAllConfirmesRV(status);
    }

    @GetMapping("/reservation/add/{idrdv}/{idpatient}")
    public Boolean addReservation(@PathVariable Integer idrdv, @PathVariable Integer idpatient){
        reservationDao.save(new Reservation(null, new Date(), rendezvousDao.getById(idrdv), patientDao.getById(idpatient), 0));
        return true;
    }

};

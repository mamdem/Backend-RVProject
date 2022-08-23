package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Class.AvailableSend;
import com.example.bamsanteback.Class.Creneaux;
import com.example.bamsanteback.Class.RendezVousSend;
import com.example.bamsanteback.Dao.MedecinDao;
import com.example.bamsanteback.Dao.PatientDao;
import com.example.bamsanteback.Dao.RendezvousDao;
import com.example.bamsanteback.Entities.Medecin;
import com.example.bamsanteback.Entities.Patient;
import com.example.bamsanteback.Entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/rendezvous/add")
    public Boolean addRendezvous (@RequestBody RendezVousSend rendezVousSend)
    {
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

    @GetMapping(value = "/rendezvous")
    public List<Rendezvous> getRendezvous(){
        return rendezvousDao.findAll();
    }

    @GetMapping(value = "/rendezvous/{id}")
    public Rendezvous getRendezvousById(@PathVariable int id){
        return rendezvousDao.findById(id);
    }

    @GetMapping("/rendezvous/all/{idmedecin}/{firstdate}/{lastdate}")
    public List<Rendezvous> getRendezBetween(@PathVariable String idmedecin, @PathVariable String firstdate, @PathVariable String lastdate){
        return rendezvousDao.getAllRVBeetween(idmedecin,firstdate, lastdate);
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
                if (!((LocalTime.parse(startTime).isBefore(LocalTime.parse(rv.getHeuredebut()))
                        && LocalTime.parse(endTime).isBefore(LocalTime.parse(rv.getHeuredebut())))
                        || LocalTime.parse(startTime).isAfter(LocalTime.parse(rv.getHeurefin()))
                        && LocalTime.parse(endTime).isAfter(LocalTime.parse(rv.getHeurefin())))){
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
};

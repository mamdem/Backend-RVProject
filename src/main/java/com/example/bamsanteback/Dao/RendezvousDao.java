package com.example.bamsanteback.Dao;

import com.example.bamsanteback.Class.Creneaux;
import com.example.bamsanteback.Class.RendezVousSend;
import com.example.bamsanteback.Entities.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public interface RendezvousDao extends JpaRepository<Rendezvous,Integer> {
    Rendezvous findById(int id);

    @Resource
    @Query(value = "select * from rendezvous where idmedecin=:idmedecin and datecreation BETWEEN :firstdate and :lastdate", nativeQuery = true)
    List<Rendezvous> getAllRVBeetween(String idmedecin, String firstdate, String lastdate);


    @Resource
    @Query(value = "select new com.example.bamsanteback.Class.Creneaux(r.heuredebut, r.heurefin,1) from Rendezvous r where r.medecin.idmedecin=:idmedecin and r.datecreation=:date", nativeQuery = false)
    List<Creneaux> getAllRVByDatereation(Integer idmedecin, Date date);
}

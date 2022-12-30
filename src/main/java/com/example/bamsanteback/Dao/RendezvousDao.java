package com.example.bamsanteback.Dao;
import com.example.bamsanteback.Class.Creneaux;
import com.example.bamsanteback.Entities.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@RepositoryRestResource
public interface RendezvousDao extends JpaRepository<Rendezvous,Integer> {
    Rendezvous findById(int id);

    @Resource
    @Query(value = "select * from rendezvous where idmedecin=:idmedecin and datecreation BETWEEN :firstdate and :lastdate", nativeQuery = true)
    List<Rendezvous> getAllRVBeetweenByMedecin(String idmedecin, String firstdate, String lastdate);

    @Resource
    @Query(value="select rv.* from rendezvous rv where rv.idrdv not in (select r.idrdv from reservation r where r.idpatient=:patient) and rv.datecreation >= now() order by rand()", nativeQuery = true)
    List<Rendezvous> getAllRVBeetween(Integer patient);

    @Resource
    @Query(value="select rv.* from rendezvous rv where rv.idmedecin=:idmedecin and rv.idrdv not in (select r.idrdv from reservation r where r.idpatient=:patient) and rv.datecreation >= now() order by rand()", nativeQuery = true)
    List<Rendezvous> getAllRVBeetweenByMedecin(Integer patient, Integer idmedecin);

    @Resource
    @Query(value="select rv.* from rendezvous rv where rv.datecreation < now() and rv.idpatient=:patient", nativeQuery = true)
    List<Rendezvous> getRVHistories(Integer patient);


    @Resource
    @Query(value = "SELECT * from rendezvous where datecreation=CURRENT_DATE and idmedecin=:idmedecin and idpatient not null", nativeQuery = true)
    List<Rendezvous> getRvToday(Integer idmedecin);

    @Resource
    @Query(value="SELECT * FROM `rendezvous` rv where rv.idpatient is null and idrdv not in (SELECT idrdv FROM reservation WHERE idpatient=:idpatient)", nativeQuery = true)
    List<Rendezvous> getAllAvailableRV(Integer idpatient);

    @Resource
    @Query(value = "select new com.example.bamsanteback.Class.Creneaux(r.heuredebut, r.heurefin,1) from Rendezvous r where r.medecin.idmedecin=:idmedecin and r.datecreation=:date", nativeQuery = false)
    List<Creneaux> getAllRVByDatereation(Integer idmedecin, Date date);

    @Modifying
    @Transactional
    @Query(value = "update rendezvous set idpatient=:idpatient where idrdv=:idrdv", nativeQuery = true)
    void reserver(Integer idpatient, Integer idrdv);

    @Query(value = "select * from rendezvous where idpatient is not null", nativeQuery = true)
    List<Rendezvous> getAllConfirmesRV(Integer status);

    @Modifying
    @Transactional
    @Query(value = "update reservation set status=:val where idrdv=:idrdv and idpatient=:idpatient", nativeQuery = true)
    void updateRvStatus(Integer idrdv, Integer val, Integer idpatient);

    @Modifying
    @Transactional
    @Query(value = "update rendezvous set idpatient=:idpatient where idrdv=:idrdv", nativeQuery = true)
    void valide(Integer idpatient,Integer idrdv);
}

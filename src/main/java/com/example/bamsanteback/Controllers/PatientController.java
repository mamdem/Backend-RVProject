package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.PatientDao;
import com.example.bamsanteback.Entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class PatientController {
    @Autowired
    PatientDao patientDao;

    public String DIRECTORY="C:/wamp64/www/imguploaded/";

    @PostMapping(value = "/patient/add")
    public ResponseEntity<Patient> addPersonne (@RequestBody Patient patient)
    {
        Patient patient1 = patientDao.save(patient);
        if(patient1 == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(patient1);
    }

    @PostMapping(value = "/patient/editurlimage/")
    public boolean updateUrlImage(@RequestBody Patient patient){
        String url = uploadBase64String(patient.getProfil(), patient.getIdpatient());
        patientDao.editUrlImage(url, patient.getIdpatient());
        return true;
    }

    String uploadBase64String(String urlimage, Integer idpatient){
        String fileName="";
        //On verifie si le type de contenu est different de text
        if (urlimage!=(null) && !urlimage.equals("")) {
            try {
                byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(urlimage);
                //On crée le nom de l'image
                fileName = "profil_patient_"+idpatient+".png";
                //directory = chemin de l'image
                String directory = this.DIRECTORY+"patient/"+ fileName;
                new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
            } catch (IOException e) {
                System.out.println(e.getMessage());
                fileName = "";
            }
        }else {
            fileName = patientDao.findById(idpatient).get().getProfil();
        }
        return fileName;
    }

    @GetMapping(value = "/personnes")
    public List<Patient> getPersonnes(){
        return patientDao.findAll();
    }

    @GetMapping(value = "/personnes/{id}")
    public Patient getPersonneById(@PathVariable int id){
        return patientDao.findById(id);
    }

    @PostMapping(value = "/patient/login")
    public Patient login(@RequestBody Patient patient){
        return patientDao.loginUser(patient.getTel(), patient.getMdp());
    }
}

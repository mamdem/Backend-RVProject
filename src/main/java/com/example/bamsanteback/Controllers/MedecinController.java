package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.MedecinDao;
import com.example.bamsanteback.Dao.StructureDao;
import com.example.bamsanteback.Dao.View;
import com.example.bamsanteback.Entities.Medecin;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MedecinController {
    @Autowired
    MedecinDao medecinDao;
    @Autowired
    StructureDao structureDao;
    public String DIRECTORY="C:/Users/MHD/Desktop/BamTech/medecinProject/src/assets/imguploaded/";

    @PostMapping(value = "/medecin/add")
    public ResponseEntity<Medecin> addPersonne (@RequestBody Medecin medecin)
    {
        Medecin medecin1 = medecinDao.save(medecin);
        if(medecin1 == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(medecin1);
    }

    @GetMapping(value = "/medecins")
    @JsonView(View.MedecinView.class)
    public List<Medecin> getMedecins(){
        return medecinDao.findAll();
    }

    @GetMapping(value = "/medecin/{id}")
    public Medecin getPersonneById(@PathVariable int id){
        return medecinDao.findById(id);
    }

    @PostMapping(value = "/medecin/login")
    public Medecin login(@RequestBody Medecin medecin){
        return medecinDao.loginUser(medecin.getTel(), medecin.getMdp());
    }

    @PutMapping(value = "/medecin/edit/{idmedecin}")
    public Medecin updateMedecin(@RequestBody Medecin medecin, @PathVariable Integer idmedecin){
        medecin.setProfil(uploadBase64String(medecin.getProfil(), medecin.getIdmedecin()));
        structureDao.saveAndFlush(medecin.getStructure());
        return medecinDao.saveAndFlush(medecin);
    }

    String uploadBase64String(String urlimage, Integer idmedecin){
        String fileName="";
        //On verifie si le type de contenu est different de text
        if (urlimage!=(null) && !urlimage.equals("")) {
            try {
                byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(urlimage);
                //On crée le nom de l'image
                fileName = "profil_medecin_"+idmedecin+".png";
                //directory = chemin de l'image
                String directory = this.DIRECTORY+"medecin/"+ fileName;
                new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
            } catch (IOException e) {
                System.out.println(e.getMessage());
                fileName = "";
            }
        }else {
            fileName = medecinDao.findById(idmedecin).get().getProfil();
        }
        return fileName;
    }
}

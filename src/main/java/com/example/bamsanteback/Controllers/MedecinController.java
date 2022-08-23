package com.example.bamsanteback.Controllers;


import com.example.bamsanteback.Dao.MedecinDao;
import com.example.bamsanteback.Dao.View;
import com.example.bamsanteback.Entities.Medecin;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MedecinController {
    @Autowired
    MedecinDao medecinDao;

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
}

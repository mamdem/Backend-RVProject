package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.PersonneDao;
import com.example.bamsanteback.Entities.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonneController {
    @Autowired
    PersonneDao personneDao;

    @PostMapping(value = "/personnes/add")
    public ResponseEntity<Personne> addPersonne (@RequestBody Personne personne)
    {
        Personne personne1 = personneDao.save(personne);
        if(personne1 == null){
            return ResponseEntity.noContent().build();
        }
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{/id}")
//                .buildAndExpand(p.getIdpersonne())
//                .toUri();
        return ResponseEntity.ok().body(personne1);
    }

    @GetMapping(value = "/personnes")
    public List<Personne> getPersonnes(){
        return personneDao.findAll();
    }

    @GetMapping(value = "/personnes/{id}")
    public Personne getPersonneById(@PathVariable int id){
        return personneDao.findById(id);
    }
}

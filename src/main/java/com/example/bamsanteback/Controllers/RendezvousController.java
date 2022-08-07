package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.RendezvousDao;
import com.example.bamsanteback.Entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RendezvousController {
    @Autowired
    private RendezvousDao rendezvousDao;

    @PostMapping(value = "/rendezvous/add")
    public ResponseEntity<Rendezvous> addRendezvous (@RequestBody Rendezvous rendezvous)
    {
        Rendezvous rendezvous1 = rendezvousDao.save(rendezvous);
        if(rendezvous1 == null){
            return ResponseEntity.noContent().build();
        }
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{/id}")
//                .buildAndExpand(p.getIdpersonne())
//                .toUri();
        return ResponseEntity.ok().body(rendezvous1);
    }

    @GetMapping(value = "/rendezvous")
    public List<Rendezvous> getRendezvous(){
        return rendezvousDao.findAll();
    }

    @GetMapping(value = "/rendezvous/{id}")
    public Rendezvous getRendezvousById(@PathVariable int id){
        return rendezvousDao.findById(id);
    }
}

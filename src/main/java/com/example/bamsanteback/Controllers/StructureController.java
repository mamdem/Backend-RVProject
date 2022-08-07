package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.StructureDao;
import com.example.bamsanteback.Entities.Rendezvous;
import com.example.bamsanteback.Entities.Structure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StructureController {
    @Autowired
    StructureDao structureDao;

    @PostMapping(value = "/structures/add")
    public ResponseEntity<Structure> addStructure (@RequestBody Structure structure)
    {
        Structure structure1 = structureDao.save(structure);
        if(structure1 == null){
            return ResponseEntity.noContent().build();
        }
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{/id}")
//                .buildAndExpand(p.getIdpersonne())
//                .toUri();
        return ResponseEntity.ok().body(structure1);
    }

    @GetMapping(value = "/structures")
    public List<Structure> getStructures(){
        return structureDao.findAll();
    }

    @GetMapping(value = "/structures/{id}")
    public Structure getStructureById(@PathVariable int id){
        return structureDao.findById(id);
    }
}

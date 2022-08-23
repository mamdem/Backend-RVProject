package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.ServiceDao;
import com.example.bamsanteback.Entities.Rendezvous;
import com.example.bamsanteback.Entities.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ServiceController {
    @Autowired
    ServiceDao serviceDao;

    @PostMapping(value = "/services/add")
    public ResponseEntity<Service> addService (@RequestBody Service service)
    {
        Service service1 = serviceDao.save(service);
        if(service1 == null){
            return ResponseEntity.noContent().build();
        }
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{/id}")
//                .buildAndExpand(p.getIdpersonne())
//                .toUri();
        return ResponseEntity.ok().body(service1);
    }

    @GetMapping(value = "/services")
    public List<Service> getServices(){
        return serviceDao.findAll();
    }

    @GetMapping(value = "/services/{id}")
    public Service getServiceById(@PathVariable int id){
        return serviceDao.findById(id);
    }
}

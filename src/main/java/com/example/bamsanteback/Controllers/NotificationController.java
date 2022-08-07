package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.NotificationDao;
import com.example.bamsanteback.Entities.Notification;
import com.example.bamsanteback.Entities.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationDao notificationDao;

    @PostMapping(value = "/notifications/add")
    public ResponseEntity<Notification> addNotification (@RequestBody Notification notification)
    {
        Notification notification1 = notificationDao.save(notification);
        if(notification1 == null){
            return ResponseEntity.noContent().build();
        }
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{/id}")
//                .buildAndExpand(p.getIdpersonne())
//                .toUri();
        return ResponseEntity.ok().body(notification1);
    }

    @GetMapping(value = "/notifications")
    public List<Notification> getNotifications(){
        return notificationDao.findAll();
    }

    @GetMapping(value = "/notifications/{id}")
    public Notification getNotificationById(@PathVariable int id){
        return notificationDao.findById(id);
    }
}

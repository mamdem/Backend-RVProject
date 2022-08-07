package com.example.bamsanteback.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Rendezvous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idrdv;
    Date datecreation;
    Date datedeb;
    Date datefin;
    String description;
    int etat;

    @ManyToOne
    @JoinColumn(name = "idmedecin",referencedColumnName = "idpersonne")
    private Personne medecin;

    @ManyToOne
    @JoinColumn(name = "idpatient",referencedColumnName = "idpersonne")
    private Personne patient;

    @OneToMany(mappedBy = "rendezvous")
    private List<Notification> notificationList;
}

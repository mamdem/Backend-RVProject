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
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idnotification;
    private Date datenotif;
    private String contenu;
    private Integer etat;

    @ManyToOne
    @JoinColumn(name = "idrdv")
    private Rendezvous rendezvous;
}

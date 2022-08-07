package com.example.bamsanteback.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpersonne;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String type;
    private String tel;

    @OneToMany(mappedBy = "medecin")
    private List<Rendezvous> rendezvousList;

    @OneToMany(mappedBy = "patient")
    private List<Rendezvous> rendezvous;

    @ManyToOne
    @JoinColumn(name = "idservice")
    private Service service;


}

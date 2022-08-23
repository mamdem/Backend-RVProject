package com.example.bamsanteback.Entities;

import com.example.bamsanteback.Dao.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.MedecinView.class)
    private Integer idmedecin;
    @JsonView(View.MedecinView.class)
    private String nom;
    @JsonView(View.MedecinView.class)
    private String prenom;
    @JsonView(View.MedecinView.class)
    private String email;
    @JsonView(View.MedecinView.class)
    private String mdp;
    @JsonView(View.MedecinView.class)
    private String tel;
    @JsonView(View.MedecinView.class)
    private String profil;

    @JsonIgnore
    @OneToMany(mappedBy = "medecin")
    private List<Rendezvous> rendezvousList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idservice")
    private Service service;


}

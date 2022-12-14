package com.example.bamsanteback.Entities;

import com.example.bamsanteback.Dao.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Medecin implements Serializable {
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
    private String genre;
    @JsonView(View.MedecinView.class)
    private String biographie;
    private Date date;
    @JsonView(View.MedecinView.class)
    private String profil;
    @JsonIgnore
    @OneToMany(mappedBy = "medecin")
    private List<Rendezvous> rendezvousList;

    //@JsonIgnore

    @ManyToOne
    @JoinColumn(name = "idservice")
    private Service service;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idstructure")
    private Structure structure;
}
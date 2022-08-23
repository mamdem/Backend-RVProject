package com.example.bamsanteback.Entities;

import com.example.bamsanteback.Class.RendezVousSend;
import com.example.bamsanteback.Dao.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Rendezvous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.MedecinView.class,View.PatientView.class,View.RendezVousView.class})
    Integer idrdv;
    @JsonView({View.MedecinView.class,View.RendezVousView.class})
    Date datecreation;
    @JsonView({View.MedecinView.class,View.RendezVousView.class})
    //@Temporal(TemporalType.TIME)
    String heuredebut;
    //@Temporal(TemporalType.TIME)
    @JsonView({View.MedecinView.class,View.RendezVousView.class})
    String heurefin;
    @JsonView({View.MedecinView.class,View.RendezVousView.class})
    String description;
    @JsonView({View.MedecinView.class,View.RendezVousView.class})
    int etat;

    @ManyToOne
    @JoinColumn(name = "idmedecin",referencedColumnName = "idmedecin")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "idpatient",referencedColumnName = "idpatient")
    private Patient patient;

    @JsonIgnore
    @OneToMany(mappedBy = "rendezvous")
    private List<Notification> notificationList;

}

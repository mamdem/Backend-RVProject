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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.MedecinView.class,View.PatientView.class})
    private Integer idpatient;
    @JsonView(View.PatientView.class)
    private String nom;
    @JsonView(View.PatientView.class)
    private String prenom;
    @JsonView(View.PatientView.class)
    private String email;
    @JsonView(View.PatientView.class)
    private String mdp;
    @JsonView(View.PatientView.class)
    private String tel;


    @OneToMany(mappedBy = "patient")
    private List<Rendezvous> rendezvous;
}
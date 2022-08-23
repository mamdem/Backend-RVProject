package com.example.bamsanteback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idadmin;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String tel;

    @ManyToOne
    @JoinColumn(name = "idstructure")
    private Structure structure;
}

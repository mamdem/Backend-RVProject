package com.example.bamsanteback.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfilSettingSend {
    private Integer idmedecin;
    private String prenom;
    private String nom;
    private String email;
    private String tel;
    private String mdp;
    private Integer idservice;
    private String profil;
    private Date date;
    private String genre;
    private String biographie;
    private Structure structure;
    private List<String> imageStructure;
}

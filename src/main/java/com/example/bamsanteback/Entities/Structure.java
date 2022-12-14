package com.example.bamsanteback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idstructure;
    private String nom;
    private String adresse;
    private Double latitude;
    private Double longitude;



    @OneToMany(mappedBy = "structure")
    private List<ImageStructure> imageStructures;

}

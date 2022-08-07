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
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idservice;
    private String nomservice;
    private String description;

    @OneToMany(mappedBy = "service")
    private List<Personne> personneList;

    @ManyToOne
    @JoinColumn(name = "idstructure")
    private Structure structure;
}

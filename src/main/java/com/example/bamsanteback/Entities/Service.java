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
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idservice;
    private String nomservice;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private List<Medecin> medecinList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idstructure")
    private Structure structure;
}

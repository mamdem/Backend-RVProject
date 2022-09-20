package com.example.bamsanteback.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idreservation;

    private Date datereservation;

    @ManyToOne
    @JoinColumn(name = "idrdv",referencedColumnName = "idrdv")
    private Rendezvous rendezvous;

    @ManyToOne
    @JoinColumn(name = "idpatient",referencedColumnName = "idpatient")
    private Patient patient;

    private Integer status;
}

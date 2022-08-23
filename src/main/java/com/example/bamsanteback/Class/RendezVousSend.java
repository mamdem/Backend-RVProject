package com.example.bamsanteback.Class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RendezVousSend {
    private Date datecreation;
    private String description;
    private int idmedecin;
    private List<Creneaux> creneaux;
}

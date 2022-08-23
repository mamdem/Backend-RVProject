package com.example.bamsanteback.Class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Creneaux {
    private String heuredebut;
    private String heurefin;
    private int idpatient;
}

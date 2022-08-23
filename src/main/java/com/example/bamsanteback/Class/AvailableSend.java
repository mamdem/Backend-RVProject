package com.example.bamsanteback.Class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AvailableSend {
    private Integer idmedecin;
    private Date date;
    private String startTime;
    private int ecartTime;
}

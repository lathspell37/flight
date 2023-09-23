package com.example.flight.flight.model;

import com.example.flight.flight.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name="flights")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//LOMBOK mucizesi, getter setter, constructorları annotation olarak tanımlamamızı sağlıyor. refactor - delombok ile açabiliriz
public class Flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String flightNumber;

    @OneToOne
    private Routes route;

    private double price;

    private Date departureTime;

    private Date arrivalTime;

    private int capacity;

    private StatusEnum status;


}

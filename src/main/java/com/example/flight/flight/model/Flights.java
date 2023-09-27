package com.example.flight.flight.model;

import com.example.flight.flight.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//LOMBOK, getter setter, constructorları annotation olarak tanımlamamızı sağlıyor. refactor - delombok ile açabiliriz
public class Flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Routes route;

    private double price;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime departureTime;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime arrivalTime;

    private int capacity;

    private StatusEnum status;


}

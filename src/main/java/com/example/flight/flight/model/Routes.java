package com.example.flight.flight.model;

import jakarta.persistence.*;

@Entity
@Table(name="routes")
public class Routes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL) //Cascade, Airports tablosunda değişiklik olursa, Routes da etkileniyor.
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Airports source;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Airports destination;

    private double distance;

    public Routes() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Airports getSource() {
        return source;
    }

    public void setSource(Airports source) {
        this.source = source;
    }

    public Airports getDestination() {
        return destination;
    }

    public void setDestination(Airports destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Routes(long id, Airports source, Airports destination, double distance) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }
}

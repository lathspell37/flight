package com.example.flight.flight.model;

import jakarta.persistence.*;

@Entity
@Table(name="airports" )
public class Airports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="airport_code")
    public int code;
    @Column(name = "airport_city")
    private String city;
    @Column(name="airport_country")
    private String country;

    public Airports() {
        super();
    }

    public Airports(int code, String city, String country) {
        this.code = code;
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

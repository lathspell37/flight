package com.example.flight.flight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="routes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Routes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Airports source;

    @ManyToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Airports destination;
    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Flights> flights;
    private double distance;

}

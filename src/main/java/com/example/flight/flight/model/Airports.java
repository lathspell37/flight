package com.example.flight.flight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.RouteMatcher;

import java.util.List;

@Entity
@Table(name="airports" )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Airports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Routes> source_airport;

    @OneToMany(mappedBy = "destination", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Routes> destination_airport;

    public int code;

    private String city;

    private String country;

}
